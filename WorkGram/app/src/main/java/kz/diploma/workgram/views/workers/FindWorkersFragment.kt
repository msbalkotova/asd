package kz.diploma.workgram.views.workers

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.fragment_find_workers.*
import kz.diploma.workgram.R
import kz.diploma.workgram.databinding.FragmentFindWorkersBinding
import kz.diploma.workgram.repositories.vo.Status
import kz.diploma.workgram.utils.Constants
import kz.diploma.workgram.utils.showToast
import kz.diploma.workgram.views.BaseFragment
import kz.diploma.workgram.views.BaseNextViewDelegate
import kz.diploma.workgram.views.common.CommonActivity
import kz.diploma.workgram.views.home.HomeActivity
import org.koin.android.ext.android.inject

class FindWorkersFragment: BaseFragment(), BaseNextViewDelegate {
    private val viewModel: FindWorkersViewModel by inject()

    private val adapter by lazy {
        FindWorkersAdapter(viewModel.categories)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentFindWorkersBinding.inflate(inflater, container, false)

        view.viewModel = viewModel
        view.delegate = this
        view.lifecycleOwner = this
        view.executePendingBindings()

        return view.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexWrap = FlexWrap.WRAP

        categoriesList.layoutManager = layoutManager
        categoriesList.adapter = adapter

        adapter.onItemClick = { viewModel.selectedCategory = it }

        viewModel.fetchCategories().observe(viewLifecycleOwner, Observer { it?.let{
            viewModel.loadingStatus.postValue(it.status)

            when(it.status){
                Status.LOADING ->{}
                Status.ERROR ->{ it.messageCode?.let{getString(it).showToast(context)}
//                    activity?.onBackPressed()
                }
                Status.SUCCESS ->{
                    it.data?.categories?.data?.let{
                        viewModel.categories.addAll(it)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }})
    }

    override fun onNextButtonClicked(view: View) {
        if(viewModel.selectedCategory != null) {
            val intent = Intent(activity, CommonActivity::class.java)
            intent.putExtra(Constants.NEXT_FRAGMENT, WorkersFragment.TAG)
            intent.putExtra("category_id", (viewModel.selectedCategory!!.id))
            intent.putExtra("title", (viewModel.selectedCategory!!.name))
            startActivity(intent)
        } else {
            getString(R.string.choose_category).showToast(context)
        }
    }

    override fun onCancelClicked(view: View) {
        activity?.onBackPressed()
    }
    companion object {
        val TAG = FindWorkersFragment::class.java.simpleName
        @JvmStatic
        fun newInstance() = FindWorkersFragment()
    }

}