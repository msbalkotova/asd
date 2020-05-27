package kz.diploma.workgram.views.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_settings.*
import kz.diploma.workgram.databinding.FragmentSettingsBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SettingsFragment: Fragment() {
    private val viewModel: SettingsViewModel by sharedViewModel()
    private val adapter by lazy {
        viewModel.infoList.let { SettingsAdapter(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentSettingsBinding.inflate(inflater, container, false)
        view.viewModel = viewModel
        view.lifecycleOwner = this
        view.executePendingBindings()

        return view.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvInfo.layoutManager = LinearLayoutManager(context)
        rvInfo.adapter = adapter

        viewModel.infoList = viewModel.getInfo()!!
    }

    companion object {
        val TAG = SettingsFragment::class.java.simpleName
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}