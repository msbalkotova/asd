package kz.diploma.workgram.models.categories

import java.io.Serializable
import java.util.*

data class CategoryDto(
    var id: Int? = null,
    var created_at: String? = null,
    val deleted_at: String? = null,
    val updated_at: String? = null,
    var have: Boolean = false,
    var name: String? = null,
    var isSelected: Boolean = false
): Serializable