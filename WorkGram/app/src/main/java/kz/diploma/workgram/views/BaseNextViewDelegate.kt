package kz.diploma.workgram.views

import android.view.View

interface BaseNextViewDelegate {
    fun onNextButtonClicked(view: View);
    fun onCancelClicked(view: View);
}