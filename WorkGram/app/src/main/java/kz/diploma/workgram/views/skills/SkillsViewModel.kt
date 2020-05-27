package kz.diploma.workgram.views.skills

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kz.diploma.workgram.models.BaseResponse
import kz.diploma.workgram.models.categories.CategoryDto
import kz.diploma.workgram.models.categories.CategoryResponseDto
import kz.diploma.workgram.repositories.HomeRepository
import kz.diploma.workgram.repositories.vo.Resource
import kz.diploma.workgram.repositories.vo.Status

class SkillsViewModel (val homeRepository: HomeRepository): ViewModel() {
    var loadingStatus: MutableLiveData<Status> = MutableLiveData()
    val categories: ArrayList<CategoryDto> = arrayListOf()
    var selectedCategories: ArrayList<Int> = arrayListOf()
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
        return homeRepository.fetchMyCategories(null, 20)
    }

    fun sendCategories(): LiveData<Resource<BaseResponse>> {
        return homeRepository.sendMyCategories(selectedCategories)
    }
}