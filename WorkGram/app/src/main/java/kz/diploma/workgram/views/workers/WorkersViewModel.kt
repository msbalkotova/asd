package kz.diploma.workgram.views.workers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import kz.diploma.workgram.models.workers.WorkerDto
import kz.diploma.workgram.repositories.vo.Status
import kz.diploma.workgram.services.ApiService
import kz.diploma.workgram.utils.paginator.WorkerDataSourceFactory

class WorkersViewModel(val apiService: ApiService): ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    var categoryId: Int? = null

    private var workerDataSourceFactory: WorkerDataSourceFactory
    private var workersData: LiveData<PagedList<WorkerDto>>
    private var loadingStatusWorkers: LiveData<Status>? = null

    val loadingStatus: MutableLiveData<Status> = MutableLiveData()

    init {
        val pagedConfig = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()

        workerDataSourceFactory = WorkerDataSourceFactory(categoryId, apiService, compositeDisposable)
        workersData = LivePagedListBuilder(workerDataSourceFactory, pagedConfig).build()
        loadingStatusWorkers = Transformations.switchMap(workerDataSourceFactory.mutableDataSource) { it.state }
    }

    fun configureWorkers(){
        val pagedConfig = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()

        workerDataSourceFactory = WorkerDataSourceFactory(categoryId, apiService, compositeDisposable)
        workersData = LivePagedListBuilder(workerDataSourceFactory, pagedConfig).build()
        loadingStatusWorkers = Transformations.switchMap(workerDataSourceFactory.mutableDataSource) { it.state }
    }
    fun workersList(): LiveData<PagedList<WorkerDto>> = workersData
    fun workersState(): LiveData<Status>? = loadingStatusWorkers


}