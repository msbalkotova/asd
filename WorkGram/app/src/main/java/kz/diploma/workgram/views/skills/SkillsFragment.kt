package kz.diploma.workgram.views.skills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.fragment_my_skills.*
import kz.diploma.workgram.R
import kz.diploma.workgram.databinding.FragmentMySkillsBinding
import kz.diploma.workgram.repositories.vo.Status
import kz.diploma.workgram.utils.showToast
import kz.diploma.workgram.views.BaseFragment
import kz.diploma.workgram.views.BaseNextViewDelegate
import org.koin.android.ext.android.inject

class SkillsFragment : BaseFragment(), BaseNextViewDelegate {
    private val viewModel: SkillsViewModel by inject()

    private val adapter by lazy {
        SkillsAdapter(viewModel.categories)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentMySkillsBinding.inflate(inflater, container, false)

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

        myCategoriesList.layoutManager = layoutManager
        myCategoriesList.adapter = adapter

        adapter.onItemClick = {
            if (viewModel.selectedCategories.contains(it.id)) {
            viewModel.selectedCategories.removeAt(viewModel.selectedCategories.indexOf(it.id))
        } else {
            viewModel.selectedCategories.add(it.id!!)
        } }

        viewModel.fetchCategories().observe(viewLifecycleOwner, Observer { it?.let{
            viewModel.loadingStatus.postValue(it.status)

            when(it.status){
                Status.LOADING ->{}
                Status.ERROR ->{ it.messageCode?.let{getString(it).showToast(context)}
//                    activity?.onBackPressed()
                }
                Status.SUCCESS ->{
                    it.data?.categories?.data?.let {
                        viewModel.categories.addAll(it)
                        adapter.notifyDataSetChanged()
                    }
                        viewModel.categories.forEach {
                            if(it.have){
                                viewModel.selectedCategories.add(it.id!!)
                            }

                        }
                }
                }
            }
        })
    }

    override fun onNextButtonClicked(view: View) {
        if(viewModel.selectedCategories.isNotEmpty()) {
            observeAddInterests()
        } else getString(R.string.choose_category).showToast(context)

    }

    private fun observeAddInterests(){
        viewModel.sendCategories().observe(viewLifecycleOwner, Observer { it?.let{
            viewModel.loadingStatus.postValue(it.status)
            when(it.status){
                Status.LOADING ->{}
                Status.ERROR ->{ it.messageCode?.let{getString(it).showToast(context)}
                    activity?.onBackPressed()
                }
                Status.SUCCESS ->{
                    getString(R.string.success).showToast(context)
                        activity?.onBackPressed()

                }
                }
            }
        })
    }

    override fun onCancelClicked(view: View) {
        //activity?.onBackPressed()
    }
    companion object {
        val TAG = SkillsFragment::class.java.simpleName
        @JvmStatic
        fun newInstance() = SkillsFragment()
    }

}