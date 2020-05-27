package kz.diploma.workgram.repositories.utils

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kz.diploma.workgram.models.ApiResponse
import kz.diploma.workgram.repositories.vo.Resource


abstract class NetworkBoundRepository<ResultType, RequestType>
internal constructor() {

    private val result: MediatorLiveData<Resource<ResultType>> = MediatorLiveData()

    init {
        val loadedFromDB = this.loadFromDb()
        result.addSource(loadedFromDB) { data ->
            result.removeSource(loadedFromDB)
            if (shouldFetch(data)) {
                result.postValue(Resource.loading(null))
                fetchFromNetwork(loadedFromDB)
            } else {
                result.addSource<ResultType>(loadedFromDB) { newData ->
                    setValue(
                        Resource.success(
                            newData
                        )
                    )
                }
            }
        }
    }

    private fun fetchFromNetwork(loadedFromDB: LiveData<ResultType>) {
        val apiResponse = fetchService()
        result.addSource(apiResponse) { response ->
            response?.let {
                when (response.isSuccessful) {
                    true -> {
                        response.body?.let {
                            saveLoadedData(it)
                        }
                        setValue(Resource.success(response.body as ResultType?))
                    }
                    false -> {
                        result.removeSource(loadedFromDB)
                        onFetchFailed(response.errorCode)
                        setValue(Resource.error(it.msgCode, null))
                    }
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

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun shouldFetch(data: ResultType): Boolean
}