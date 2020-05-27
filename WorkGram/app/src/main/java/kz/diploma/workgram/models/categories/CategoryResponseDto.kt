package kz.diploma.workgram.models.categories

import kz.diploma.workgram.models.BaseResponse

data class CategoryResponseDto (
    val categories: CategoriesPageDto? = null
): BaseResponse()