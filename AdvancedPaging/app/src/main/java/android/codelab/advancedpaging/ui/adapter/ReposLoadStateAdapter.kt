package android.codelab.advancedpaging.ui.adapter

import android.codelab.advancedpaging.ui.viewholder.ReposLoadStateViewHolder
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class ReposLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<ReposLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: ReposLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ReposLoadStateViewHolder {
        return ReposLoadStateViewHolder.create(parent, retry)
    }
}