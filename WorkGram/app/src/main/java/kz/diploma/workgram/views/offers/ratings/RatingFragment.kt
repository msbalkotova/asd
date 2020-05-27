package kz.diploma.workgram.views.offers.ratings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_my_ratings.*
import kz.diploma.workgram.R
import kz.diploma.workgram.databinding.FragmentMyRatingsBinding
import kz.diploma.workgram.repositories.vo.Status
import kz.diploma.workgram.utils.showToast
import kz.diploma.workgram.views.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RatingFragment: BaseFragment() {
    private val viewModel: RatingViewModel by sharedViewModel()

    private val ratingAdapter = RatingAdapter()
    var status: Status? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyRatingsBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvMyRatings.adapter = ratingAdapter
        rvMyRatings.layoutManager = LinearLayoutManager(context)

        viewModel.configureOffers()
        viewModel.offersList().observe(viewLifecycleOwner, Observer {
            if (status == Status.SUCCESS) {
                if(it.isNotEmpty())
                    ratingAdapter.submitList(it)
                else  getString(R.string.no_offers_implemented).showToast(context)

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
        val TAG = RatingFragment::class.java.simpleName
        @JvmStatic
        fun newInstance() = RatingFragment()
    }
}