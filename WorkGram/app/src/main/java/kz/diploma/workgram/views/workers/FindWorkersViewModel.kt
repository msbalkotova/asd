package kz.diploma.workgram.views.workers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kz.diploma.workgram.models.categories.CategoriesPageDto
import kz.diploma.workgram.models.categories.CategoryDto
import kz.diploma.workgram.models.categories.CategoryResponseDto
import kz.diploma.workgram.repositories.HomeRepository
import kz.diploma.workgram.repositories.vo.Resource
import kz.diploma.workgram.repositories.vo.Status

class FindWorkersViewModel(val homeRepository: HomeRepository): ViewModel() {
    var loadingStatus: MutableLiveData<Status> = MutableLiveData()
    val categories: ArrayList<CategoryDto> = arrayListOf()
    var selectedCategory: CategoryDto ?= null
//    private var categoriesData: LiveData<PagedList<CategoryDto>>

//    init {
//        val pagedConfig = PagedList.Config.Builder()
//            .setPageSize(10)
//            .setEnablePlaceholders(false)
//            .build()
//
//        //init recommendations
//        categoriesDataFactory = RecomDataSourceFactory(apiService, compositeDisposable)
//        categoriesData = LivePagedListBuilder(recomCourseDataFractory, pagedConfig).build()
//        loadingStatusCources = Transformations.switchMap(recomCourseDataFractory.mutableDataSource){
//            it.state
//        }

    fun fetchCategories(): LiveData<Resource<CategoryResponseDto>> {
        return homeRepository.fetchCategories(null, 10)
    }


}