package kz.diploma.workgram.views.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.view.*
import kz.diploma.workgram.R
import kz.diploma.workgram.databinding.FragmentMainBinding
import kz.diploma.workgram.utils.Constants
import kz.diploma.workgram.utils.replace
import kz.diploma.workgram.views.BaseFragment
import kz.diploma.workgram.views.common.CommonActivity
import kz.diploma.workgram.views.employee.EmployeeFragment
import kz.diploma.workgram.views.employer.EmployerFragment
import kz.diploma.workgram.views.profiles.ProfileFragment
import kz.diploma.workgram.views.settings.SettingsFragment
import org.koin.android.ext.android.inject

class HomeFragment: BaseFragment(), HomeNextViewDelegate {
    private val viewModel: HomeViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentMainBinding.inflate(inflater, container, false)

        view.viewModel = viewModel
        view.delegate = this
        view.lifecycleOwner = this
        view.executePendingBindings()

        return view.root
    }


    override fun onNextButtonClicked(view: View) {
        val intent = Intent(activity, CommonActivity::class.java)
        when(view.getId()){
             R.id.settingsCard->{
                 intent.putExtra(Constants.NEXT_FRAGMENT, SettingsFragment.TAG)
                 intent.putExtra("title", getString(R.string.settings))
             }
            R.id.employeeCard->{
                intent.putExtra(Constants.NEXT_FRAGMENT, EmployeeFragment.TAG)
                intent.putExtra("title", getString(R.string.employee))
            }
            R.id.employerCard->{
                intent.putExtra(Constants.NEXT_FRAGMENT, EmployerFragment.TAG)
                intent.putExtra("title", getString(R.string.employer))
            }
            R.id.chatCard->{
                intent.putExtra(Constants.NEXT_FRAGMENT, EmployeeFragment.TAG)
                intent.putExtra("title", getString(R.string.settings))
            }
            R.id.accountCard->{
                intent.putExtra(Constants.NEXT_FRAGMENT, ProfileFragment.TAG)
                intent.putExtra("title", getString(R.string.account))
            }
        }
        startActivity(intent)

    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}