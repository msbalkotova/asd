package kz.diploma.workgram.views.offers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import kz.diploma.workgram.models.offers.OfferDto
import kz.diploma.workgram.models.workers.WorkerDto
import kz.diploma.workgram.repositories.vo.Status
import kz.diploma.workgram.services.ApiService
import kz.diploma.workgram.utils.paginator.OfferDataSourceFactory
import kz.diploma.workgram.utils.paginator.WorkerDataSourceFactory
import kz.diploma.workgram.views.BaseFragment
import kz.diploma.workgram.views.BaseNextViewDelegate

class OffersViewModel(val apiService: ApiService): ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private var offerDataSourceFactory: OfferDataSourceFactory
    private var offersData: LiveData<PagedList<OfferDto>>
    private var loadingStatusOffers: LiveData<Status>? = null

    val loadingStatus: MutableLiveData<Status> = MutableLiveData()

    init {
        val pagedConfig = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()

        offerDataSourceFactory = OfferDataSourceFactory(apiService, compositeDisposable, 1)
        offersData = LivePagedListBuilder(offerDataSourceFactory, pagedConfig).build()
        loadingStatusOffers = Transformations.switchMap(offerDataSourceFactory.mutableDataSource) { it.state }
    }

    fun configureOffers(){
        val pagedConfig = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()

        offerDataSourceFactory = OfferDataSourceFactory(apiService, compositeDisposable, 1)
        offersData = LivePagedListBuilder(offerDataSourceFactory, pagedConfig).build()
        loadingStatusOffers = Transformations.switchMap(offerDataSourceFactory.mutableDataSource) { it.state }
    }
    fun offersList(): LiveData<PagedList<OfferDto>> = offersData
    fun offersState(): LiveData<Status>? = loadingStatusOffers
}