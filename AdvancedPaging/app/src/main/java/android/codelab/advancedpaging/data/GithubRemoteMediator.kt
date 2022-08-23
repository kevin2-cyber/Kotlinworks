package android.codelab.advancedpaging.data

import android.codelab.advancedpaging.api.GithubService
import android.codelab.advancedpaging.db.RepoDatabase
import android.codelab.advancedpaging.model.Repo
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator

@OptIn(ExperimentalPagingApi::class)
class GithubRemoteMediator(
    private val query: String,
    private val service: GithubService,
    private val repoDatabase: RepoDatabase
) : RemoteMediator<Int, Repo>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Repo>): MediatorResult {
        TODO("Not yet implemented")
    }


}