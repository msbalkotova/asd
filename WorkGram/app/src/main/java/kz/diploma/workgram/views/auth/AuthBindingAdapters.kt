package kz.diploma.workgram.views.auth

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import kz.diploma.workgram.R
import kz.diploma.workgram.repositories.vo.Status
import kz.diploma.workgram.utils.helpers.ErrorCatcher

object AuthBindingAdapters {
    @JvmStatic
    @BindingAdapter("android:setVisibility")
    fun setVisibility(view: View, status: Status?) {
        status?.let{
            when(it){
                Status.LOADING ->{
                    view.visibility = View.VISIBLE
                }

                else ->{
                    view.visibility = View.GONE
                }
            }
        }?: run{
            view.visibility = View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("android:loginStatus")
    fun loginStatusTextSetter(textView: TextView, status: ErrorCatcher?) {
        val res = textView.context.resources
        status?.let {
            when(it){
                ErrorCatcher.SUCCESS ->{
                    textView.text = res.getString(R.string.login_to_account_desc)
                    textView.setTextColor(res.getColor(R.color.darkBlue))
                }

                ErrorCatcher.PASS_LENGTH ->{
                    textView.text = res.getString(R.string.pass_length_error)
                    textView.setTextColor(res.getColor(R.color.redPink))
                }
            }
        }?:run{
            textView.text = res.getString(R.string.login_to_account_desc)
            textView.setTextColor(res.getColor(R.color.darkBlue))
        }
    }
}