package kz.diploma.workgram.views.home

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kz.diploma.workgram.R
import kz.diploma.workgram.utils.Constants

object HomeBindingAdapters {
    @JvmStatic
    @BindingAdapter("android:helloName")
    fun setName(textView: TextView, fullName: String?) {
        if(!fullName.isNullOrEmpty()){
            textView.text = textView.context.getString(R.string.welcome_name, fullName)
        }else{
            textView.text = textView.context.getString(R.string.welcome)
        }
    }
    @JvmStatic
    @BindingAdapter("android:imgUrl")
    fun setVisibility(imageView: ImageView, url: String?) {
        url?.let {
            val ctx = imageView.context
            Glide
                .with(ctx)
                .load(Constants.BASE_URL + it)
                .placeholder(R.drawable.common_full_open_on_phone)
                .centerCrop()
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("android:scale")
    fun setRating(ratingBar: RatingBar, scale: Double?) {
        scale?.let{
            ratingBar.rating = it.toFloat()
        }
    }

}