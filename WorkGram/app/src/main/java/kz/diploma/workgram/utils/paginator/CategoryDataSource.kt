package kz.diploma.workgram.utils.paginator

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import kz.diploma.workgram.models.categories.CategoryDto
import kz.diploma.workgram.repositories.vo.Status
import kz.diploma.workgram.services.ApiService

class CategoryDataSource ()
//    val categoryId: Int?,
//    val apiService: ApiService,
//    val compositeDisposable: CompositeDisposable
//) : PageKeyedDataSource<Int, CategoryDto>() {
//
//    var state: MutableLiveData<Status> = MutableLiveData()
//    private var retryCompletable: Completable? = null

//    override fun loadInitial(
//        params: LoadInitialParams<Int>,
//        callback: LoadInitialCallback<Int, CategoryDto>
//    ) {
//        updateState(Status.LOADING)
//        compositeDisposable.add(
//            apiService.getCategories(categoryId, 1)
//                .subscribe({ response ->
//                    response?.courses?.data?.let {
//                        updateState(Status.SUCCESS)
//                        callback.onResult(it, null, 2)
//                    }
//                }, {
//                    updateState(Status.ERROR)
//                    setRetry(Action { loadInitial(params, callback) })
//                })
//        )
//    }
//
//    override fun loadAfter(
//        params: LoadParams<Int>,
//        callback: LoadCallback<Int, CourseItemDto>
//    ) {
//        compositeDisposable.add(
//            apiService.getCoursesById(categoryId, params.key)
//                .subscribe(
//                    { response ->
//                        response?.courses?.data?.let {
//                            updateState(Status.SUCCESS)
//                            callback.onResult(it, params.key + 1)
//                        }
//                    },
//                    {
//                        updateState(Status.ERROR)
//                        setRetry(Action { loadAfter(params, callback) })
//                    }
//                )
//        )
//    }
//
//    override fun loadBefore(
//        params: LoadParams<Int>,
//        callback: LoadCallback<Int, CourseItemDto>
//    ) {
//
//    }
//
//    fun retry() {
//        if (retryCompletable != null) {
//            compositeDisposable.add(
//                retryCompletable!!
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe()
//            )
//        }
//    }
//
//    private fun updateState(state: Status) {
//        this.state.postValue(state)
//    }
//
//    private fun setRetry(action: Action?) {
//        retryCompletable = if (action == null) null else Completable.fromAction(action)
//    }
//}