package android.codelab.advancedpaging

import android.codelab.advancedpaging.api.GithubService
import android.codelab.advancedpaging.data.GithubRepository
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
object Injection {

    /**
     * Creates an instance of [GithubRepository] based on the [GithubService] and a
     * [GithubLocalCache]
     */
    private fun provideGithubRepository(): GithubRepository {
        return GithubRepository(GithubService.create())
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */

    fun provideViewModelFactory(owner: SavedStateRegistryOwner): ViewModelFactory.Factory {
        return ViewModelFactory(owner, provideGithubRepository())
    }
}