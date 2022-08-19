package android.codelab.pagingcodelab.data

import java.time.LocalDateTime

private val firstArticleCreatedTime = LocalDateTime.now()

class ArticleRepository {

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