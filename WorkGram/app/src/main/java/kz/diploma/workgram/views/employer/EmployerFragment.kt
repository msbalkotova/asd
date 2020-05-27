package kz.diploma.workgram.views.employer

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kz.diploma.workgram.R
import kz.diploma.workgram.databinding.FragmentEmployerBinding
import kz.diploma.workgram.utils.Constants
import kz.diploma.workgram.utils.replace
import kz.diploma.workgram.views.BaseFragment
import kz.diploma.workgram.views.BaseNextViewDelegate
import kz.diploma.workgram.views.common.CommonActivity
import kz.diploma.workgram.views.employee.EmployeeFragment
import kz.diploma.workgram.views.offers.createOffer.CreateOfferFragment
import kz.diploma.workgram.views.offers.created.MyOffersFragment
import kz.diploma.workgram.views.workers.FindWorkersFragment
import org.koin.android.ext.android.inject

class EmployerFragment: BaseFragment(), BaseNextViewDelegate {
    private val viewModel: EmployerViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentEmployerBinding.inflate(inflater, container, false)

        view.viewModel = viewModel
        view.delegate = this
        view.lifecycleOwner = this
        view.executePendingBindings()

        return view.root
    }

    override fun onNextButtonClicked(view: View) {
        val intent = Intent(activity, CommonActivity::class.java)
        when(view.getId()){
            R.id.findWorkersCard->{
                intent.putExtra(Constants.NEXT_FRAGMENT, FindWorkersFragment.TAG)
                intent.putExtra("title", getString(R.string.find_workers))
            }
            R.id.myOffersCard->{
                intent.putExtra(Constants.NEXT_FRAGMENT, MyOffersFragment.TAG)
                intent.putExtra("title", getString(R.string.my_offers))
            }
            R.id.createProjectCard->{
                intent.putExtra(Constants.NEXT_FRAGMENT, CreateOfferFragment.TAG)
                intent.putExtra("title", getString(R.string.create_project))
            }
        }
        startActivity(intent)
    }

    override fun onCancelClicked(view: View) {
        // activity?.onBackPressed()
    }

    companion object {
        val TAG = EmployerFragment::class.java.simpleName
        @JvmStatic
        fun newInstance() = EmployerFragment()
    }
}
