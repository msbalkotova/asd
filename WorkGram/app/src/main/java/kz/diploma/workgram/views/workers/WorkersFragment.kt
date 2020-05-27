package kz.diploma.workgram.views.workers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_workers.*
import kz.diploma.workgram.R
import kz.diploma.workgram.databinding.FragmentWorkersBinding
import kz.diploma.workgram.models.categories.CategoryDto
import kz.diploma.workgram.repositories.vo.Status
import kz.diploma.workgram.utils.showToast
import kz.diploma.workgram.views.BaseFragment
import kz.diploma.workgram.views.BaseNextViewDelegate
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class WorkersFragment: BaseFragment() {
    private val viewModel: WorkersViewModel by sharedViewModel()

    private val workersAdapter = WorkersAdapter()
    var status: Status? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.categoryId = it.getInt("category_id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWorkersBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvWorkers.adapter = workersAdapter
        rvWorkers.layoutManager = LinearLayoutManager(context)

        viewModel.configureWorkers()
        viewModel.workersList().observe(viewLifecycleOwner, Observer { it?.let{
            if (status == Status.SUCCESS) {
                if(it.isNotEmpty())
                    workersAdapter.submitList(it)
                else  getString(R.string.no_offers).showToast(context)

            } else {
                getString(R.string.generic_error).showToast(context)
//                activity?.onBackPressed()
            }
        }})

        viewModel.workersState()?.observe(viewLifecycleOwner, Observer { it?.let{
            it?.let {
                status = it
                viewModel.loadingStatus.postValue(it)
            }
        }})
    }

    companion object {
        val TAG = WorkersFragment::class.java.simpleName

        @JvmStatic
        fun newInstance(category_id: Int) =
            WorkersFragment().apply {
                arguments = Bundle().apply {
                    putInt("category_id", category_id)
                }
            }
    }
}