package kz.diploma.workgram.views.offers.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kz.diploma.workgram.databinding.FragmentOffersBinding
import kz.diploma.workgram.databinding.FragmentProjectBinding
import kz.diploma.workgram.views.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProjectFragment: BaseFragment() {
    private val viewModel: ProjectViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProjectBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        return binding.root
    }
}