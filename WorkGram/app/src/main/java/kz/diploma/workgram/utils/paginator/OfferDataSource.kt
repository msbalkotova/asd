package kz.diploma.workgram.utils.paginator

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import kz.diploma.workgram.models.offers.OfferDto
import kz.diploma.workgram.models.workers.WorkerDto
import kz.diploma.workgram.repositories.vo.Status
import kz.diploma.workgram.services.ApiService

class OfferDataSource(val apiService: ApiService,
                      val compositeDisposable: CompositeDisposable,
                      val type: Int
): PageKeyedDataSource<Int, OfferDto>()  {
    var state: MutableLiveData<Status> = MutableLiveData()
    private var retryCompletable: Completable? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, OfferDto>
    ) {
        updateState(Status.LOADING)
        when(type) {
            1 -> {
                compositeDisposable.add(
                    apiService.getProjects(1)
                        .subscribe({ response ->
                            response?.projects?.data?.let {
                                updateState(Status.SUCCESS)
                                callback.onResult(it, null, 2)
                            }
                        }, {
                            updateState(Status.ERROR)
                            setRetry(Action { loadInitial(params, callback) })
                        })
                )
            }
            2 -> {
                compositeDisposable.add(
                    apiService.getImplementedProjects(1)
                        .subscribe({ response ->
                            response?.projects?.data?.let {
                                updateState(Status.SUCCESS)
                                callback.onResult(it, null, 2)
                            }
                        }, {
                            updateState(Status.ERROR)
                            setRetry(Action { loadInitial(params, callback) })
                        })
                )
            }
            3 -> {
                compositeDisposable.add(
                    apiService.getCreatedProjects(1)
                        .subscribe({ response ->
                            response?.projects?.data?.let {
                                updateState(Status.SUCCESS)
                                callback.onResult(it, null, 2)
                            }
                        }, {
                            updateState(Status.ERROR)
                            setRetry(Action { loadInitial(params, callback) })
                        })
                )
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, OfferDto>
    ) {
        when (type) {
            1 -> {
                compositeDisposable.add(
                    apiService.getProjects(params.key)
                        .subscribe(
                            { response ->
                                response?.projects?.data?.let {
                                    updateState(Status.SUCCESS)
                                    callback.onResult(it, params.key + 1)
                                }
                            },
                            {
                                updateState(Status.ERROR)
                                setRetry(Action { loadAfter(params, callback) })
                            }
                        )
                )
            }
            2 -> {
                compositeDisposable.add(
                    apiService.getImplementedProjects(params.key)
                        .subscribe(
                            { response ->
                                response?.projects?.data?.let {
                                    updateState(Status.SUCCESS)
                                    callback.onResult(it, params.key + 1)
                                }
                            },
                            {
                                updateState(Status.ERROR)
                                setRetry(Action { loadAfter(params, callback) })
                            }
                        )
                )
            }
            3 -> {
                compositeDisposable.add(
                    apiService.getCreatedProjects(params.key)
                        .subscribe(
                            { response ->
                                response?.projects?.data?.let {
                                    updateState(Status.SUCCESS)
                                    callback.onResult(it, params.key + 1)
                                }
                            },
                            {
                                updateState(Status.ERROR)
                                setRetry(Action { loadAfter(params, callback) })
                            }
                        )
                )
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, OfferDto>
    ) {

    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(
                retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            )
        }
    }

    private fun updateState(state: Status) {
        this.state.postValue(state)
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }
}