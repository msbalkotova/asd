package kz.diploma.workgram.views

interface ItemSelector {
    fun <T>onItemSelected(item: T)
}