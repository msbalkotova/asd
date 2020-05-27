package kz.diploma.workgram.views.profiles

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_profile.*
import kz.diploma.workgram.R
import kz.diploma.workgram.databinding.FragmentProfileBinding
import kz.diploma.workgram.repositories.vo.Status
import kz.diploma.workgram.utils.Constants
import kz.diploma.workgram.utils.showAlertDialog
import kz.diploma.workgram.utils.showToast
import kz.diploma.workgram.utils.view.ViewUtils
import kz.diploma.workgram.views.BaseFragment
import kz.diploma.workgram.views.auth.RegisterActivity
import kz.diploma.workgram.views.common.CommonActivity
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.io.File

class ProfileFragment: BaseFragment() {
    private val viewModel: ProfileViewModel by sharedViewModel()

    var status: Status? = null


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        cvAvatar.setOnClickListener {
            onAvatarClicked()
        }
        tvLogout.setOnClickListener {
            navigateToLogout()
        }
        cvUpdate.setOnClickListener {
            updateProfile()
        }
        observeUserProfile()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        return binding.root
    }

    private fun observeUserProfile(){
        viewModel.getProfile().observe(viewLifecycleOwner, Observer { it?.let{
            viewModel.loadingStatus.postValue(it.status)
            when(it.status){
                Status.LOADING ->{}
                Status.ERROR ->{it.messageCode?.let{getString(it).showToast(context)}}
                Status.SUCCESS -> {
                    it.data?.profile?.let {
                        viewModel.profile.postValue(it)
                    }
                }
            }
        }})
    }

    private fun onAvatarClicked(){
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), Constants.READ_EXTERNAL_STORAGE)
        } else {
            navigateToSelectPhoto()
        }
    }

    private fun navigateToSelectPhoto(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(
            Intent.createChooser(
                intent,
                getString(R.string.select_photo)
            ), Constants.SELECT_PHOTO_REQUEST
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                Constants.OPEN_SETTINGS_REQUEST -> {
                    observeUserProfile()
                    }

                Constants.SELECT_PHOTO_REQUEST ->{
                    data?.data?.let {
                        viewModel.avatarUrl = it
                        observeUploadAvatar(it)
                    }
                }
            }
        }
    }

    private fun observeUploadAvatar(uri: Uri){
        try {
            val path = ViewUtils.getPathFromURI(uri, requireContext())
            val file = File(path)

            viewModel.loadingStatus.postValue(Status.LOADING)
            viewModel.uploadAvatar(file).observe(viewLifecycleOwner, Observer {
                it?.let {
                    if (it.success) {
                        viewModel.loadingStatus.postValue(Status.SUCCESS)
                        viewModel.avatarUrl?.let {
                            avatar.setImageURI(it)
                        }
                    } else {
                        viewModel.loadingStatus.postValue(Status.ERROR)
                        getString(R.string.generic_error).showAlertDialog(context)
                    }
                }
            })
        }catch (e: Exception){
            getString(R.string.generic_error).showAlertDialog(context)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Constants.READ_EXTERNAL_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    navigateToSelectPhoto()
                }
            }
        }
    }

    fun navigateToLogout(){
        viewModel.logout()
        val i = Intent(context, RegisterActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

    fun updateProfile(){
        viewModel.updateProfile(nameInput.text.toString(), surnameInput.text.toString(), nickInput.text.toString(),
            phoneInput.text.toString(), descriptionInput.text.toString())
            .observe(viewLifecycleOwner, Observer { it?.let {
                when(it.status){
                    Status.LOADING ->{}
                    Status.ERROR ->{it.messageCode?.let{getString(it).showToast(context)}}
                    Status.SUCCESS ->{getString(R.string.success).showToast(context)
                        val intent = Intent(activity, CommonActivity::class.java)
                        intent.putExtra(Constants.NEXT_FRAGMENT, TAG)
                        intent.putExtra("title", getString(R.string.account))
                        startActivity(intent)

                    }
                }
            }})
    }

    companion object {
        val TAG = ProfileFragment::class.java.simpleName
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}