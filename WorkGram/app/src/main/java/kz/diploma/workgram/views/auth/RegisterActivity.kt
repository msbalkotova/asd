package kz.diploma.workgram.views.auth

import android.os.Bundle
import kz.diploma.workgram.R
import kz.diploma.workgram.utils.add
import kz.diploma.workgram.views.BaseActivity

class RegisterActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val enterPhoneFragment = LoginFragment.newInstance()
        enterPhoneFragment.add(supportFragmentManager, true)
    }
}