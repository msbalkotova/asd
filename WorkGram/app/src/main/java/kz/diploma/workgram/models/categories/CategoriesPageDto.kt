package kz.diploma.workgram.models.categories

data class CategoriesPageDto(
    val current_page: Int? = null,
    val data: List<CategoryDto>? = null,
    val first_page_url: String? = null,
    val from: Int? = null,
    val last_page: Int? = null,
    val last_page_url: String? = null,
    val next_page_url: String? = null,
    val path: String? = null,
    val per_page: Int? = null,
    val prev_page_url: String? = null,
    val to: Int? = null,
    val total: Int? = null
)