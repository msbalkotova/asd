package kz.diploma.workgram.views.profiles

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kz.diploma.workgram.models.BaseResponse
import kz.diploma.workgram.models.profile.UserDto
import kz.diploma.workgram.models.profile.UserResponse
import kz.diploma.workgram.repositories.HomeRepository
import kz.diploma.workgram.repositories.vo.Resource
import kz.diploma.workgram.repositories.vo.Status
import java.io.File

class ProfileViewModel(val repository: HomeRepository) : ViewModel() {
    val profile: MutableLiveData<UserDto> = MutableLiveData()
    val loadingStatus: MutableLiveData<Status> = MutableLiveData()
    var avatarUrl: Uri? = null



    fun logout(){
        repository.logout()
    }

    fun getProfile(): LiveData<Resource<UserResponse>> {
        return repository.getProfile()
    }

    fun uploadAvatar(file: File): MutableLiveData<BaseResponse> {
        return repository.uploadAvatar(file)
    }

    fun updateProfile(name: String, surname: String, nick: String,
                      phone: String, description: String): LiveData<Resource<BaseResponse>> {
        return repository.updateProfile(name, surname, nick, phone, description)
    }
}