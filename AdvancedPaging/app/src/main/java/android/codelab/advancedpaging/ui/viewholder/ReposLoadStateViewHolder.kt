package android.codelab.advancedpaging.ui.viewholder

import android.codelab.advancedpaging.R
import android.codelab.advancedpaging.databinding.RepoLoadStateFooterViewItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView

class ReposLoadStateViewHolder(
    private val binding: RepoLoadStateFooterViewItemBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }

        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): ReposLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.repo_load_state_footer_view_item, parent, false)
            val binding = RepoLoadStateFooterViewItemBinding.bind(view)
            return ReposLoadStateViewHolder(binding, retry)
        }
    }
}