package kz.diploma.workgram.utils.paginator

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import kz.diploma.workgram.models.workers.WorkerDto
import kz.diploma.workgram.services.ApiService

class WorkerDataSourceFactory(
    val categoryId: Int?,
    val apiService: ApiService,
    val compositeDisposable: CompositeDisposable) : DataSource.Factory<Int, WorkerDto>()
{
    val mutableDataSource: MutableLiveData<WorkerDataSource> = MutableLiveData()
    private lateinit var mWorkerDataSource: WorkerDataSource

    override fun create(): DataSource<Int, WorkerDto> {
        mWorkerDataSource = WorkerDataSource(categoryId, apiService, compositeDisposable)
        mutableDataSource.postValue(mWorkerDataSource)

        return mWorkerDataSource
    }
}