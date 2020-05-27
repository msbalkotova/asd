package kz.diploma.workgram.views.employee

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kz.diploma.workgram.R
import kz.diploma.workgram.databinding.FragmentEmployeeBinding
import kz.diploma.workgram.utils.Constants
import kz.diploma.workgram.views.BaseFragment
import kz.diploma.workgram.views.BaseNextViewDelegate
import kz.diploma.workgram.views.common.CommonActivity
import kz.diploma.workgram.views.offers.OffersFragment
import kz.diploma.workgram.views.offers.ratings.RatingFragment
import kz.diploma.workgram.views.skills.SkillsFragment
import org.koin.android.ext.android.inject

class EmployeeFragment: BaseFragment(), BaseNextViewDelegate {
    private val viewModel: EmployeeViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentEmployeeBinding.inflate(inflater, container, false)

        view.viewModel = viewModel
        view.delegate = this
        view.lifecycleOwner = this
        view.executePendingBindings()

        return view.root
    }

    override fun onNextButtonClicked(view: View) {
        val intent = Intent(activity, CommonActivity::class.java)

        when(view.getId()){
            R.id.mySkillsCard->{
                intent.putExtra(Constants.NEXT_FRAGMENT, SkillsFragment.TAG)
                intent.putExtra("title", getString(R.string.my_skills))
            }
            R.id.offersCard->{
                intent.putExtra(Constants.NEXT_FRAGMENT, OffersFragment.TAG)
                intent.putExtra("title", getString(R.string.offers))
            }
            R.id.myRatingsCard->{
                intent.putExtra(Constants.NEXT_FRAGMENT, RatingFragment.TAG)
                intent.putExtra("title", getString(R.string.my_ratings))
            }
        }
        startActivity(intent)
    }

    override fun onCancelClicked(view: View) {
        //activity?.onBackPressed()
    }

    companion object {
        val TAG = EmployeeFragment::class.java.simpleName
        @JvmStatic
        fun newInstance(): EmployeeFragment {
            return EmployeeFragment()
        }
    }

}