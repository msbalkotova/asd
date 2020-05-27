package kz.diploma.workgram.views.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kz.diploma.workgram.models.profile.UserDto
import kz.diploma.workgram.repositories.AuthRepository
import kz.diploma.workgram.repositories.vo.Resource
import kz.diploma.workgram.repositories.vo.Status
import kz.diploma.workgram.utils.helpers.ErrorCatcher
import kz.diploma.workgram.utils.rawText

class AuthViewModel(val authRepository: AuthRepository): ViewModel() {
    var fieldChecked: MutableLiveData<ErrorCatcher> = MutableLiveData()
    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var loadingStatus: MutableLiveData<Status> = MutableLiveData()

    fun loginExec(): LiveData<Resource<UserDto>> {
        val email = email.value ?:""
        val password = password.value ?: ""

        return authRepository.authRequest("login", email, password)
    }

    fun emailAndPasswordValidation(){
        val password = password.value ?: ""
        val email = email.value ?: ""
            if(email.length > 5 && password.length >=8){
                fieldChecked.postValue(ErrorCatcher.SUCCESS)
            }else if(email.length < 5){
                fieldChecked.postValue(ErrorCatcher.PHONE_REQ)
            }else{
                fieldChecked.postValue(ErrorCatcher.PASS_LENGTH)
            }
        }
}