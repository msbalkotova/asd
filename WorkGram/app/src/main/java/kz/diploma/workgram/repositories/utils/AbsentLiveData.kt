package kz.diploma.workgram.repositories.utils

import androidx.lifecycle.LiveData

class AbsentLiveData<T : Any?>(): LiveData<T>() {
    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}