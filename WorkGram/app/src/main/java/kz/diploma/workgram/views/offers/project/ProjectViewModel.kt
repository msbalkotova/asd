package kz.diploma.workgram.views.offers.project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kz.diploma.workgram.models.offers.OfferDto
import kz.diploma.workgram.models.profile.UserDto
import kz.diploma.workgram.repositories.vo.Status

class ProjectViewModel: ViewModel() {
    val loadingStatus: MutableLiveData<Status> = MutableLiveData()
    var project: OfferDto? = null
    var myOffer: Boolean? = false
    var offer: Boolean? = false
    var user: UserDto? = null

}