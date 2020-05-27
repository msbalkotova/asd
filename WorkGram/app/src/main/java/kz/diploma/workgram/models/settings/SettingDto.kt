package kz.diploma.workgram.models.settings

data class SettingDto(
    var id: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var isSelected: Boolean? = false
)
