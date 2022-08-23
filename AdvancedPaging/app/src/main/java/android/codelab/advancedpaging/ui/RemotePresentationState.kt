package android.codelab.advancedpaging.ui

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.scan

/**
 * An enum representing the status of items in the as fetched by the
 * [Pager] when used with a [RemoteMediator]
 */

enum class RemotePresentationState {
    INITIAL, REMOTE_LOADING, SOURCE_LOADING, PRESENTED
}

/**
 * Reduces [CombinedLoadStates] into [RemotePresentationState]. It operates ton the assumption that
 * successful [RemoteMediator] fetches always cause invalidation of the [PagingSource] as in the
 * case of the [PagingSource] provide by Room.
 */

@OptIn(ExperimentalCoroutinesApi::class)
fun Flow<CombinedLoadStates>.asPresentationState(): Flow<SearchRepositoriesActivity.RemotePresentationState> =
    scan(SearchRepositoriesActivity.RemotePresentationState.INITIAL) {
            state, loadState ->
        when(state) {
            SearchRepositoriesActivity.RemotePresentationState.PRESENTED -> when(loadState.mediator?.refresh) {
                is LoadState.Loading -> SearchRepositoriesActivity.RemotePresentationState.REMOTE_LOADING
                else -> state
            }
            SearchRepositoriesActivity.RemotePresentationState.INITIAL -> when(loadState.mediator?.refresh) {
                is LoadState.Loading -> SearchRepositoriesActivity.RemotePresentationState.REMOTE_LOADING
                else -> state
            }
            SearchRepositoriesActivity.RemotePresentationState.REMOTE_LOADING -> when(loadState.source.refresh) {
                is LoadState.Loading -> SearchRepositoriesActivity.RemotePresentationState.SOURCE_LOADING
                else -> state
            }
            SearchRepositoriesActivity.RemotePresentationState.SOURCE_LOADING -> when(loadState.source.refresh) {
                is LoadState.NotLoading -> SearchRepositoriesActivity.RemotePresentationState.PRESENTED
                else -> state
            }
        }
    }
        .distinctUntilChanged()