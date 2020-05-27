package kz.diploma.workgram.views

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kz.diploma.workgram.R
import kz.diploma.workgram.models.errors.SessionExpired
import kz.diploma.workgram.utils.LocaleManager
import kz.diploma.workgram.views.auth.RegisterActivity
import kz.diploma.workgram.views.profiles.ProfileViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.android.ext.android.inject

open class BaseActivity: AppCompatActivity() {
    private val viewModel: ProfileViewModel by inject()

    private var isExpiredShowed = false

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleManager.onAttach(newBase!!, "kk"))
    }

    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }

    private fun showSessionExpiredDialog() {
        isExpiredShowed = true
        val builder = AlertDialog.Builder(this)
            .setTitle(R.string.alert_title)
            .setMessage(R.string.unauthorized_message)
            .setCancelable(false)
            .setPositiveButton(R.string.ok) { dialogInterface, i ->
                dialogInterface.dismiss()
                navigateToLogout()
            }

        builder.show()
    }

    private fun navigateToLogout(){
        viewModel.logout()

        val i = Intent(this, RegisterActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(sessionExpired: SessionExpired) {
        if (!isExpiredShowed && sessionExpired.isExpired) {
            showSessionExpiredDialog()
        }
    }
}