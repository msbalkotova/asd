package kz.diploma.workgram.views.home

import androidx.lifecycle.ViewModel
import kz.diploma.workgram.repositories.HomeRepository

class HomeViewModel(homeRepository: HomeRepository): ViewModel() {
    val userName = homeRepository.getUser().name

}