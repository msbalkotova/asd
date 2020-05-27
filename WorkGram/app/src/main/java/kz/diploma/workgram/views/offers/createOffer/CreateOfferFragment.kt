package kz.diploma.workgram.views.offers.createOffer

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_create_project.*
import kz.diploma.workgram.databinding.FragmentCreateProjectBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class CreateOfferFragment: Fragment() {
    private val viewModel: CreateOfferViewModel by sharedViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentCreateProjectBinding.inflate(inflater, container, false)

        view.viewModel = viewModel
        view.lifecycleOwner = this
        view.executePendingBindings()

        return view.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        startTimePicker.setOnClickListener {

        }
//        tvWithDrawConditions.paintFlags = tvWithDrawConditions.paintFlags or Paint.UNDERLINE_TEXT_FLAG
//        viewSendRequest.setOnClickListener {
//            if(viewModel.checkAmount(etAmount.text.toString())){
//                observeWithDrawRequest(etAmount.text.toString().toDouble(), etComment.text.toString())
//            }else{
//                getString(R.string.fill_all_fields).showToast(context)
//            }
//        }
//
//        tvWithDrawConditions.setOnClickListener {
//            navigateWebView()
//        }
    }

    private fun observeWithDrawRequest(amount: Double, comment: String?){
//        viewModel.createProject(amount, comment).observe(viewLifecycleOwner, Observer { it?.let{
//            viewModel.loadingStatus.postValue(it.status)
//            when(it.status){
//                Status.LOADING ->{}
//                Status.ERROR ->{it.messageCode?.let{getString(it).showToast(context)}}
//                Status.SUCCESS ->{
//                    it.data?.success?.let{
//                        if(it){
//                            getString(R.string.success_create).showAlertDialog(context)
//                            activity?.finish()
//                        }else{
//                            getString(R.string.generic_error).showAlertDialog(context)
//                        }
//                    }?:run{
//                        getString(R.string.generic_error).showToast(context)
//                    }
//                }
//            }
//        }})
    }

    private fun datePicker() {

    }


    companion object {
        val TAG = CreateOfferFragment::class.java.simpleName
        @JvmStatic
        fun newInstance(): CreateOfferFragment {
            return CreateOfferFragment()
        }
    }
}