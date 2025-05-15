package android.codelab.pagingcodelab.ui

import android.codelab.pagingcodelab.data.Article
import android.codelab.pagingcodelab.data.createdText
import android.codelab.pagingcodelab.databinding.ArticleViewholderBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * View Holder for a [Article] RecyclerView list item.
 */
class ArticleViewHolder(
    private val binding: ArticleViewholderBinding
) : RecyclerView.ViewHolder(binding.root) {
    /** Binds the article to the view. */
    fun bind(article: Article) {
        binding.apply {
            binding.title.text = article.title
            binding.description.text = article.description
            binding.created.text = article.createdText
        }
    }
}