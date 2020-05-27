package kz.diploma.workgram.views.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kz.diploma.workgram.R
import kz.diploma.workgram.utils.add
import kz.diploma.workgram.views.BaseActivity

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val homeFragment = HomeFragment.newInstance()
        homeFragment.add(supportFragmentManager, true)
    }

    fun finishActivity(){
        setResult(Activity.RESULT_OK, Intent())
        finish()
    }
    override fun onBackPressed() {
        val stackCount = supportFragmentManager.backStackEntryCount
        if(stackCount >= 1){
            supportFragmentManager.popBackStack()
        }else{
            finishActivity()
        }
    }
}