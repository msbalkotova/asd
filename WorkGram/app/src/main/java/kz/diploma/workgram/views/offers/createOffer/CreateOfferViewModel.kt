package kz.diploma.workgram.views.offers.createOffer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import kz.diploma.workgram.R
import kz.diploma.workgram.models.BaseResponse
import kz.diploma.workgram.repositories.HomeRepository
import kz.diploma.workgram.repositories.vo.Resource
import kz.diploma.workgram.repositories.vo.Status

class CreateOfferViewModel(val homeRepository: HomeRepository): ViewModel() {
    val loadingStatus: MutableLiveData<Status> = MutableLiveData()

    fun createProject(): LiveData<Resource<BaseResponse>> {
        val params: HashMap<String, String> = hashMapOf()
        return homeRepository.createProject(params)
    }
}