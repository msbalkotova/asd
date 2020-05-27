package kz.diploma.workgram.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.goodiebag.pinview.Pinview
import java.io.File
import java.io.InputStream
import android.widget.ImageView
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import kz.diploma.workgram.R
import kz.diploma.workgram.utils.view.closeKeyboard
import kz.diploma.workgram.utils.view.openKeyboard

fun Fragment.add(
    fragmentManager: FragmentManager?, isAddToStack: Boolean?
) {
    val fragmentTransaction = fragmentManager?.beginTransaction()
    fragmentTransaction?.add(kz.diploma.workgram.R.id.container, this, this.tag)
    fragmentTransaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
    isAddToStack?.let {
        if (isAddToStack) {
            fragmentTransaction?.addToBackStack(tag)
        }
    }
    fragmentTransaction?.commit()
}

fun Fragment.replace(
    fragmentManager: FragmentManager?, isAddToStack: Boolean?
) {
    val fragmentTransaction = fragmentManager?.beginTransaction()
    fragmentTransaction?.replace(kz.diploma.workgram.R.id.container, this, this.javaClass.name)
    fragmentTransaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
    isAddToStack?.let {
        if (isAddToStack) {
            fragmentTransaction?.addToBackStack(this.javaClass.name)
        }
    }
    fragmentTransaction?.commit()
}

fun Fragment.slideUpReplace(
    fragmentManager: FragmentManager?, isAddToStack: Boolean?
){
    val fragmentTransaction = fragmentManager?.beginTransaction()
    fragmentTransaction?.setCustomAnimations(kz.diploma.workgram.R.animator.slide_in_up, 0, 0, kz.diploma.workgram.R.animator.slide_out_up)
//    fragmentTransaction?.setCustomAnimations(R.anim.slide_in_up, 0,0, R.anim.slide_in_down)
    fragmentTransaction?.replace(kz.diploma.workgram.R.id.container, this, this.tag)
    isAddToStack?.let {
        if (isAddToStack) {
            fragmentTransaction?.addToBackStack(tag)
        }
    }
    fragmentTransaction?.commit()
}

fun String.showToast(
    context: Context?
) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun String.rawText(): String {
    val strBuff = StringBuffer()
    var c: Char
    for (i in 0 until this.length) {
        c = this.get(i)
        if (Character.isDigit(c)) {
            strBuff.append(c)
        }
    }
    return strBuff.toString()
}

fun String.showAlertDialog(context: Context?) {
    context?.let {
        val builder1 = AlertDialog.Builder(it)
        builder1.setTitle(context.getString(kz.diploma.workgram.R.string.alert_title))
        builder1.setMessage(this)
        builder1.setCancelable(true)
        builder1.setPositiveButton(
            "Ok"
        ) { dialog, id -> dialog.cancel() }

        val alert11 = builder1.create()
        alert11.show()
    }
}

fun Activity.verifyAvailableNetwork(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

fun FragmentActivity.closeKeyboard() {
    currentFocus?.closeKeyboard()
}

fun FragmentActivity.openKeyboard() {
    currentFocus?.openKeyboard()
}

fun Pinview.setStatusView(codeLength: Int){
    if(codeLength == 4){
        this.setPinBackgroundRes(kz.diploma.workgram.R.drawable.card_success_border)
    }else{
        this.setPinBackgroundRes(kz.diploma.workgram.R.drawable.card_error_border)
    }
}

fun FragmentManager.switch( newFrag: Fragment, tag: String) {
    var current = findFragmentByTag(tag)
    beginTransaction()
        .apply {

            primaryNavigationFragment?.let { hide(it) }
            current?.let{
                show(it)
            }?:run{
                current = newFrag
                add(kz.diploma.workgram.R.id.container, current!!, tag)
            }
        }
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        .setPrimaryNavigationFragment(current)
        .setReorderingAllowed(true)
        .commitNowAllowingStateLoss()
}

fun Fragment.convertDpToPixel(dp: Float): Int {
    val scale = Resources.getSystem().displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun InputStream.toFile(path: String) {
    File(path).outputStream().use { this.copyTo(it) }
}


fun ImageView.setImage(url: String){
    Glide
        .with(this.context)
        .load(Constants.BASE_URL + url)
        .placeholder(R.drawable.ic_action_name)
        .centerCrop()
        .into(this)
}