package kz.diploma.workgram.views.auth

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_login.*
import kz.diploma.workgram.MainActivity
import kz.diploma.workgram.R
import kz.diploma.workgram.databinding.FragmentLoginBinding
import kz.diploma.workgram.repositories.vo.Status
import kz.diploma.workgram.utils.helpers.ErrorCatcher
import kz.diploma.workgram.utils.openKeyboard
import kz.diploma.workgram.utils.showAlertDialog
import kz.diploma.workgram.views.BaseFragment
import kz.diploma.workgram.views.home.HomeActivity
import org.koin.android.ext.android.inject

class LoginFragment : BaseFragment(), NextViewDelegate {
    private val viewModel: AuthViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentLoginBinding.inflate(inflater, container, false)

        view.viewModel = viewModel
        view.delegate = this
        view.lifecycleOwner = this
        view.executePendingBindings()

        return view.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViews()
        observeViewModels()
    }

    private fun initViews(){
        activity?.openKeyboard()
    }

    private fun observeViewModels(){
        viewModel.fieldChecked.observe(viewLifecycleOwner, Observer { it?.let{
            when(it){
                ErrorCatcher.SUCCESS ->{
                    viewEnterEmail.setBackgroundColor(Color.TRANSPARENT)
                    viewEnterPassword.setBackgroundColor(Color.TRANSPARENT)

                    observeLoginRequest()
                    viewModel.fieldChecked.postValue(null)
                }

                ErrorCatcher.PHONE_REQ ->{
                    viewEnterEmail.setBackgroundColor(Color.TRANSPARENT)
                    viewEnterEmail.setBackgroundResource(R.drawable.input_top_rounded_error)
                }

                ErrorCatcher.PASS_LENGTH ->{
                    viewEnterPassword.setBackgroundColor(Color.TRANSPARENT)
                    viewEnterPassword.setBackgroundResource(R.drawable.input_top_rounded_error)
                }
            }
        } })
    }

    private fun observeLoginRequest(){
        viewModel.loginExec().observe(viewLifecycleOwner, Observer { it?.let{
            viewModel.loadingStatus.postValue(it.status)
            when(it.status){
                Status.LOADING->{}
                Status.ERROR->{
                    it.messageCode?.let{ getString(it).showAlertDialog(context)}
                }
                Status.SUCCESS ->{
                    val intent = Intent(activity, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)

                }
            }
        }})
    }
    override fun onNextButtonClicked(view: View) {
        password.clearFocus()
        viewModel.emailAndPasswordValidation()
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}
