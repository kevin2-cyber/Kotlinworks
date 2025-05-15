package android.codelab.pagingcodelab.data

/**
 * Repository class that mimics fetching [Article] instances from an asynchronous source.
 */
class ArticleRepository {

    fun articlePagingSource() = ArticlePagingSource()
}