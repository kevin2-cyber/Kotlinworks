package android.codelab.pagingcodelab.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDateTime

private val firstArticleCreatedTime = LocalDateTime.now()

/**
 * Repository class that mimics fetching [Article] instances from an asynchronous source.
 */
class ArticleRepository {

    /**
     * Exposed singular stream of [Article] instances
     */
    val articleStream: Flow<List<Article>> = flowOf(
        (0..500).map { number ->
            Article(
                id = number,
                title = "Article $number",
                description = "This describes article $number",
                created = firstArticleCreatedTime.minusDays(number.toLong())
            )
        }
    )
}