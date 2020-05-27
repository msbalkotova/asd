package kz.diploma.workgram.repositories.utils

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kz.diploma.workgram.models.ApiResponse
import kz.diploma.workgram.repositories.vo.Resource

abstract class NetworkOnlyRepository<ResultType, RequestType>
internal constructor() {

    private val result: MediatorLiveData<Resource<ResultType>> = MediatorLiveData()

    init {
        result.postValue(Resource.loading(null))
        val apiResponse = fetchService()
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            when (response?.isSuccessful) {
                true -> {
                    response.body?.let { saveLoadedData(it) }
                    setValue(Resource.success(response.body as ResultType?))
                }
                false -> {
                    onFetchFailed(response.errorCode)
                    setValue(Resource.error(response.msgCode, null))
                }
            }
        }

    }

    @WorkerThread
    protected abstract fun saveLoadedData(item: RequestType)

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        result.value = newValue
    }

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }

    @MainThread
    protected abstract fun fetchService(): LiveData<ApiResponse<RequestType>>

    @MainThread
    protected abstract fun onFetchFailed(errCode: Int?)
}