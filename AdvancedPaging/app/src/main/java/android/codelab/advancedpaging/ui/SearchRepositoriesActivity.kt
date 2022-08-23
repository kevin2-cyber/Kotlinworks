package android.codelab.advancedpaging.ui


import android.codelab.advancedpaging.Injection
import android.codelab.advancedpaging.databinding.ActivitySearchRepositoriesBinding
import android.codelab.advancedpaging.ui.adapter.ReposAdapter
import android.codelab.advancedpaging.ui.adapter.ReposLoadStateAdapter
import android.codelab.advancedpaging.ui.viewmodel.SearchRepositoriesViewModel
import android.codelab.advancedpaging.ui.viewmodel.UiAction
import android.codelab.advancedpaging.ui.viewmodel.UiModel
import android.codelab.advancedpaging.ui.viewmodel.UiState
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.*
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchRepositoriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchRepositoriesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // get the view model
        val viewModel = ViewModelProvider(this,
            Injection.provideViewModelFactory(this,
                this))[SearchRepositoriesViewModel::class.java]

        // add dividers between RecyclerView's row items
        val decoration = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        binding.list.addItemDecoration(decoration)

        // bind the state
        binding.bindState(
            viewModel.state,
            viewModel.pagingDataFlow,
            viewModel.accept
        )
    }

    /**
     * Binds the [UiState] provided  by the [SearchRepositoriesViewModel] to the UI,
     * and allows the UI to feed back user actions to it.
     */

    private fun ActivitySearchRepositoriesBinding.bindState(
        uiState: StateFlow<UiState>,
        pagingData: Flow<PagingData<UiModel>>,
        uiActions: (UiAction) -> Unit
    ) {
        val repoAdapter = ReposAdapter()
        val header = ReposLoadStateAdapter { repoAdapter.retry() }
        list.adapter = repoAdapter.withLoadStateHeaderAndFooter(
            header = header,
            footer = ReposLoadStateAdapter { repoAdapter.retry() }
        )

        bindSearch(
            uiState,
            uiActions
        )

        bindList(
            header,
            repoAdapter,
            uiState,
            pagingData,
            uiActions,
        )
    }

    private fun ActivitySearchRepositoriesBinding.bindSearch(
        uiState: StateFlow<UiState>,
        onQueryChanged: (UiAction.Search) -> Unit
    ) {
        searchRepo.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateRepoListFromInput(onQueryChanged)
                true
            } else {
                false
            }
        }
        searchRepo.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateRepoListFromInput(onQueryChanged)
                true
            } else {
                false
            }
        }

        lifecycleScope.launch {
            uiState
                .map { it.query }
                .distinctUntilChanged()
                .collect(searchRepo::setText)
        }

//        uiState
//            .map(UiState::query)
//            .distinctUntilChanged()
//            .observe(this@SearchRepositoriesActivity,searchRepo::setText)
    }

    private fun ActivitySearchRepositoriesBinding.updateRepoListFromInput(
        onQueryChanged: (UiAction.Search) -> Unit
    ) {
        searchRepo.text.trim().let {
            if (it.isNotEmpty()) {
                list.scrollToPosition(0)
                onQueryChanged(UiAction.Search(it.toString()))
            }
        }
    }

    private fun ActivitySearchRepositoriesBinding.bindList(
        header: ReposLoadStateAdapter,
        repoAdapter: ReposAdapter,
        uiState: StateFlow<UiState>,
        pagingData: Flow<PagingData<UiModel>>,
        onScrollChanged: (UiAction.Scroll) -> Unit
    ) {

        retryButton.setOnClickListener { repoAdapter.retry() }

        list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy != 0) onScrollChanged(UiAction.Scroll(uiState.value.query))
            }
        })

        val notLoading = repoAdapter.loadStateFlow
        // Only emit when REFRESH LoadState for the paging source changes
            .asPresentationState()
        // Only react to cases where REFRESH completes i.e ., NotLoading
            .map { it == RemotePresentationState.PRESENTED }

        val hasNotScrolledForCurrentSearch = uiState
            .map { it.hasNotScrolledForCurrentSearch }
            .distinctUntilChanged()

        val shouldScrollTop = combine(
            notLoading,
            hasNotScrolledForCurrentSearch,
            Boolean::and
        )
            .distinctUntilChanged()

        lifecycleScope.launch {
            pagingData.collectLatest(repoAdapter::submitData)
        }

        lifecycleScope.launch{
            shouldScrollTop.collect{ shouldScroll ->
                if (shouldScroll) list.scrollToPosition(0)
            }
        }

        lifecycleScope.launch {
            repoAdapter.loadStateFlow.collect { loadState ->
                val isListEmpty = loadState.refresh is LoadState.NotLoading && repoAdapter.itemCount == 0
                // show empty list
                emptyList.isVisible = isListEmpty
                // only show the list if refresh succeeds
                list.isVisible = !isListEmpty
                // show loading spinner during initial load or refresh
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                // Show the retry state if initial load or refresh fails.
                retryButton.isVisible = loadState.source.refresh is LoadState.Error
                // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error

                errorState?.let {
                    Toast.makeText(
                        this@SearchRepositoriesActivity,
                        "\uD83D\uDE28 Whoops ${it.error}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        lifecycleScope.launch {
            repoAdapter.loadStateFlow.collect { loadState ->
                // Show a retry header if there was an error refreshing, and items were previously
                // cached OR default to the default prepend state
                header.loadState = loadState.mediator
                    ?.refresh
                    ?.takeIf { it is LoadState.Error && repoAdapter.itemCount > 0 }
                    ?: loadState.prepend

                val isListEmpty = loadState.refresh is LoadState.NotLoading && repoAdapter.itemCount == 0
                // show empty list
                emptyList.isVisible = isListEmpty
                // Only show the list if refresh succeeds, either from the the local db or the remote.
                list.isVisible =  loadState.source.refresh is LoadState.NotLoading || loadState.mediator?.refresh is LoadState.NotLoading
                // Show loading spinner during initial load or refresh.
                progressBar.isVisible = loadState.mediator?.refresh is LoadState.Loading
                // Show the retry state if initial load or refresh fails.
                retryButton.isVisible = loadState.mediator?.refresh is LoadState.Error && repoAdapter.itemCount == 0
            }
        }

//        uiState
//            .map(UiState::searchResult)
//            .distinctUntilChanged()
//            .observe(this@SearchRepositoriesActivity) { result ->
//                when(result) {
//                    is RepoSearchResult.Success -> {
//                        showEmptyList(result.data.isEmpty())
//                        repoAdapter.submitList(result.data)
//                    }
//                    is RepoSearchResult.Error -> {
//                        Toast.makeText(
//                            this@SearchRepositoriesActivity,
//                            "\uD83D\uDE28 Whoops $result.message}",
//                            Toast.LENGTH_LONG
//                        ).show()
//                    }
//                }
//            }
    }

//    private fun ActivitySearchRepositoriesBinding.showEmptyList(
//        show: Boolean
//    ) {
//        emptyList.isVisible = show
//        list.isVisible = !show
//    }


    enum class RemotePresentationState {
        INITIAL, REMOTE_LOADING, SOURCE_LOADING, PRESENTED
    }

}