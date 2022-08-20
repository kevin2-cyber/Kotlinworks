package android.codelab.advancedpaging.ui

import android.codelab.advancedpaging.data.GithubRepository
import android.codelab.advancedpaging.model.Repo
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * ViewModel for the [SearchRepositoriesActivity] screen.
 * The ViewModel works with the [GithubRepository] to get the data.
 */
class SearchRepositoriesViewModel(
    private val repository: GithubRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    /**
     * Stream of immutable states representative of the UI.
     */
    val state: StateFlow<UiState>

    val pagingDataFlow: Flow<PagingData<Repo>>

    /**
     * Processor of side effects from the UI which in turn feedback into [state]
     */
    val accept: (UiAction) -> Unit

    init {
        val initialQuery: String =
            savedStateHandle[LAST_SEARCH_QUERY] ?: DEFAULT_QUERY
        val lastQueryScrolled: String =
            savedStateHandle[LAST_QUERY_SCROLLED] ?: DEFAULT_QUERY
        val actionStateFlow = MutableSharedFlow<UiAction>()
        val searches = actionStateFlow
            .filterIsInstance<UiAction.Search>()
            .distinctUntilChanged()
            .onStart {
                emit(UiAction.Search(initialQuery))
            }
        val queriesScrolled = actionStateFlow
            .filterIsInstance<UiAction.Scroll>()
            .distinctUntilChanged()
        // This is shared to keep the flow "hot" while caching the last query scrolled,
        // otherwise each flatMapLatest invocation would lose the last query scrolled,
            .shareIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                1
            )
            .onStart {
                emit(UiAction.Scroll(lastQueryScrolled))
            }

        pagingDataFlow = searches
            .flatMapLatest {
                searchRepo(it.query)
            }
            .cachedIn(viewModelScope)

        state = combine(
            searches,
            queriesScrolled,
            ::Pair
        ).map {
            (search, scroll) ->
            UiState(
                search.query,
                scroll.currentQuery,
                // if the search query matches the scroll query, the user has scrolled
            search.query != scroll.currentQuery
        )
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
        UiState()
        )

        accept = {
            action ->
            viewModelScope.launch {
                actionStateFlow.emit(action)
            }
        }

//        state = queryLiveData
//            .distinctUntilChanged()
//            .switchMap { queryString ->
//                liveData {
//                    val uiState = repository.getSearchResultStream(queryString)
//                        .map {
//                            UiState(
//                                query = queryString,
//                                searchResult = it
//                            )
//                        }
//                        .asLiveData(Dispatchers.Main)
//                    emitSource(uiState)
//                }
//            }
//
//        accept = {
//            action ->
//            when(action){
//                is UiAction.Search -> queryLiveData.postValue(action.query)
//                is UiAction.Scroll -> if (action.shouldFetchMore) {
//                    val immutableQuery = queryLiveData.value
//                    if (immutableQuery != null) {
//                        viewModelScope.launch {
//                            repository.requestMore(immutableQuery)
//                        }
//                    }
//                }
//            }
//        }
    }

    override fun onCleared() {
        savedStateHandle[LAST_SEARCH_QUERY] = state.value.query
        savedStateHandle[LAST_QUERY_SCROLLED] = state.value.lastQueryScrolled
        super.onCleared()
    }

    private fun searchRepo(queryString: String): Flow<PagingData<Repo>> =
        repository.getSearchResultStream(queryString)
}
//
//private val UiAction.Scroll.shouldFetchMore
//    get() = visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount


sealed class UiAction {
    data class Search(val query: String) : UiAction()
    data class Scroll(
        val currentQuery: String
//        val visibleItemCount: Int,
//        val lastVisibleItemPosition: Int,
//        val totalItemCount: Int
    ) : UiAction()
}


data class UiState(
    val query: String = DEFAULT_QUERY,
    val lastQueryScrolled: String = DEFAULT_QUERY,
    val hasNotScrolledForCurrentSearch: Boolean = false
)

private const val VISIBLE_THRESHOLD = 5
private const val LAST_SEARCH_QUERY: String = "last_search_query"
private const val DEFAULT_QUERY = "Android"
// This is outside the ViewModel class, but in the same file
private const val LAST_QUERY_SCROLLED: String = "last_query_scrolled"