package kz.diploma.workgram.views.offers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_offers.*
import kz.diploma.workgram.R
import kz.diploma.workgram.databinding.FragmentOffersBinding
import kz.diploma.workgram.repositories.vo.Status
import kz.diploma.workgram.utils.showToast
import kz.diploma.workgram.views.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class OffersFragment: BaseFragment() {
    private val viewModel: OffersViewModel by sharedViewModel()

    private val offersAdapter = OffersAdapter()
    var status: Status? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOffersBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvOffers.adapter = offersAdapter
        rvOffers.layoutManager = LinearLayoutManager(context)

        viewModel.configureOffers()
        viewModel.offersList().observe(viewLifecycleOwner, Observer {
            if (status == Status.SUCCESS) {
                if(it.isNotEmpty())
                    offersAdapter.submitList(it)
                else  getString(R.string.no_offers).showToast(context)

            } else {
                getString(R.string.generic_error).showToast(context)
//                activity?.onBackPressed()
            }
        })

        viewModel.offersState()?.observe(viewLifecycleOwner, Observer {
            it?.let {
                status = it
                viewModel.loadingStatus.postValue(it)
            }
        })
    }

    companion object {
        val TAG = OffersFragment::class.java.simpleName
        @JvmStatic
        fun newInstance() = OffersFragment()
    }
}