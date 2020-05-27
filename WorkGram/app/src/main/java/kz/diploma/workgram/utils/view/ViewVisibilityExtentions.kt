package kz.diploma.workgram.utils.view

import android.animation.*
import android.view.View
import android.content.Context
import android.view.inputmethod.InputMethodManager
import kz.diploma.workgram.utils.view.ViewUtils.Companion.slideAnimator


/** Set the View visibility to VISIBLE and eventually animate the View alpha till 100% */
fun View.visible(animate: Boolean = true) {
    if (animate) {
        animate().alpha(1f).setDuration(500).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                visibility = View.VISIBLE
            }
        })
    } else {
        visibility = View.VISIBLE
    }
}

/** Set the View visibility to INVISIBLE and eventually animate view alpha till 0% */
fun View.invisible(animate: Boolean = true) {
    hide(View.INVISIBLE, animate)
}

/** Set the View visibility to GONE and eventually animate view alpha till 0% */
fun View.gone(animate: Boolean = true) {
    hide(View.GONE, animate)
}

/** Convenient method that chooses between View.visible() or View.invisible() methods */
fun View.visibleOrInvisible(show: Boolean, animate: Boolean = true) {
    if (show) visible(animate) else invisible(animate)
}

/** Convenient method that chooses between View.visible() or View.gone() methods */
fun View.visibleOrGone(show: Boolean, animate: Boolean = true) {
    if (show) visible(animate) else gone(animate)
}

private fun View.hide(hidingStrategy: Int, animate: Boolean = true) {
    if (animate) {
        animate().alpha(0f).setDuration(300).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                visibility = hidingStrategy
            }
        })
    } else {
        visibility = hidingStrategy
    }
}

fun View.rorateClockwise() {
    val rotate = ObjectAnimator.ofFloat(this, "rotation", 180f, 0f)
    rotate.duration = 500
    rotate.start()
}

fun View.rorateAntiClockwise() {
    val rotate = ObjectAnimator.ofFloat(this, "rotation", 0f, 180f)
    rotate.duration = 500
    rotate.start()
}

fun View.expand(endHeight: Int){
    val mAnimator = slideAnimator(0, endHeight, this)

    this.visibility = View.VISIBLE
    mAnimator.start()
}

fun View.collapse(){
    val finalHeight = this.height
    val mAnimator = slideAnimator(finalHeight, 0, this)

    mAnimator.addListener(object : Animator.AnimatorListener {
        override fun onAnimationEnd(animator: Animator) {
            this@collapse.visibility = View.GONE
        }

        override fun onAnimationStart(animator: Animator) {}

        override fun onAnimationCancel(animator: Animator) {}

        override fun onAnimationRepeat(animator: Animator) {}
    })
    mAnimator.start()
}

fun View.openKeyboard() {
    requestFocus()
    val inputMethodManager = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, 0)
}

fun View.closeKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}
