package kz.diploma.workgram.di

import kz.diploma.workgram.views.auth.AuthViewModel
import kz.diploma.workgram.views.employee.EmployeeViewModel
import kz.diploma.workgram.views.employer.EmployerViewModel
import kz.diploma.workgram.views.home.HomeViewModel
import kz.diploma.workgram.views.offers.OffersViewModel
import kz.diploma.workgram.views.offers.createOffer.CreateOfferViewModel
import kz.diploma.workgram.views.offers.created.MyOffersViewModel
import kz.diploma.workgram.views.offers.ratings.RatingViewModel
import kz.diploma.workgram.views.profiles.ProfileViewModel
import kz.diploma.workgram.views.settings.SettingsViewModel
import kz.diploma.workgram.views.skills.SkillsViewModel
import kz.diploma.workgram.views.workers.FindWorkersViewModel
import kz.diploma.workgram.views.workers.WorkersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel{
        AuthViewModel(get())
    }
    viewModel{
        HomeViewModel(get())
    }
    viewModel {
        EmployeeViewModel()
    }
    viewModel {
        EmployerViewModel()
    }
    viewModel {
        FindWorkersViewModel(get())
    }
    viewModel {
        SkillsViewModel(get())
    }
    viewModel {
        WorkersViewModel(get())
    }
    viewModel {
        OffersViewModel(get())
    }
    viewModel {
        RatingViewModel(get())
    }
    viewModel {
        MyOffersViewModel(get())
    }
    viewModel {
        CreateOfferViewModel(get())
    }
    viewModel {
        ProfileViewModel(get())
    }
    viewModel {
        SettingsViewModel()
    }
}