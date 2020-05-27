package kz.diploma.workgram.views.common

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_common.*
import kz.diploma.workgram.R
import kz.diploma.workgram.utils.Constants
import kz.diploma.workgram.utils.add
import kz.diploma.workgram.utils.replace
import kz.diploma.workgram.views.BaseActivity
import kz.diploma.workgram.views.employee.EmployeeFragment
import kz.diploma.workgram.views.employer.EmployerFragment
import kz.diploma.workgram.views.offers.OffersFragment
import kz.diploma.workgram.views.offers.createOffer.CreateOfferFragment
import kz.diploma.workgram.views.offers.created.MyOffersFragment
import kz.diploma.workgram.views.offers.ratings.RatingFragment
import kz.diploma.workgram.views.profiles.ProfileFragment
import kz.diploma.workgram.views.settings.SettingsFragment
import kz.diploma.workgram.views.skills.SkillsFragment
import kz.diploma.workgram.views.workers.FindWorkersFragment
import kz.diploma.workgram.views.workers.WorkersFragment

class CommonActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)


        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_36dp)
        toolbar.setNavigationOnClickListener { finish() }
        setSupportActionBar(toolbar)

        val title = intent.getStringExtra("title")
        supportActionBar?.title = title
        when (intent.getStringExtra(Constants.NEXT_FRAGMENT)) {
            WorkersFragment.TAG ->{
                showFragment(WorkersFragment.newInstance(intent.getIntExtra("category_id", 1)))
            }
            EmployeeFragment.TAG ->{
                toolbar.setNavigationIcon(R.drawable.ic_cancel_main_36dp)
                showFragment(EmployeeFragment.newInstance())
            }
            EmployerFragment.TAG ->{
                toolbar.setNavigationIcon(R.drawable.ic_cancel_main_36dp)
                showFragment(EmployerFragment.newInstance())
            }
            FindWorkersFragment.TAG ->{
                showFragment(FindWorkersFragment.newInstance())
            }
            SkillsFragment.TAG ->{
                showFragment(SkillsFragment.newInstance())
            }
            OffersFragment.TAG ->{
                showFragment(OffersFragment.newInstance())
            }
            RatingFragment.TAG ->{
                showFragment(RatingFragment.newInstance())
            }
            MyOffersFragment.TAG ->{
                showFragment(MyOffersFragment.newInstance())
            }
            SettingsFragment.TAG ->{
                showFragment(SettingsFragment.newInstance())
            }
            ProfileFragment.TAG ->{
                showFragment(ProfileFragment.newInstance())
            }
            CreateOfferFragment.TAG ->{
                val createOfferFragment = CreateOfferFragment.newInstance()
                createOfferFragment.add(supportFragmentManager, true)
                showFragment(createOfferFragment)
            }
        }

    }

    fun showFragment(fragment: Fragment, isAdd: Boolean = false) {
        fragment.replace(supportFragmentManager, isAdd)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK)
        super.onBackPressed()
    }
}