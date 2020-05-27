package kz.diploma.workgram.utils.paginator

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import kz.diploma.workgram.models.offers.OfferDto
import kz.diploma.workgram.models.workers.WorkerDto
import kz.diploma.workgram.services.ApiService

class OfferDataSourceFactory (val apiService: ApiService,
val compositeDisposable: CompositeDisposable, val type: Int) : DataSource.Factory<Int, OfferDto>()
{
    val mutableDataSource: MutableLiveData<OfferDataSource> = MutableLiveData()
    private lateinit var mOfferDataSource: OfferDataSource

    override fun create(): DataSource<Int, OfferDto> {
        mOfferDataSource = OfferDataSource(apiService, compositeDisposable, type)
        mutableDataSource.postValue(mOfferDataSource)

        return mOfferDataSource
    }
}