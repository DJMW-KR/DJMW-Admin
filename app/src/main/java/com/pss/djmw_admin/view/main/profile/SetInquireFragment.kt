package com.pss.djmw_admin.view.main.profile

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.pss.djmw_admin.R
import com.pss.djmw_admin.base.BaseFragment
import com.pss.djmw_admin.data.model.Inquire
import com.pss.djmw_admin.databinding.FragmentSetInquireBinding
import com.pss.djmw_admin.viewmodel.MainViewModel
import com.pss.djmw_admin.viewmodel.ProfileViewModel
import com.pss.djmw_admin.widget.extension.settingVisibility
import java.text.SimpleDateFormat
import java.util.*

class SetInquireFragment : BaseFragment<FragmentSetInquireBinding>(R.layout.fragment_set_inquire) {
    private val profileViewModel by activityViewModels<ProfileViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun init() {
        binding.fragment = this
        observeViewModel()
    }

    fun clickBackBtn(view: View) {
        mainViewModel.setActionView(true)
        this.findNavController().popBackStack()
    }

    private fun observeViewModel(){
        profileViewModel.eventError.observe(this,{
            when(it){
                0-> shortShowToast("문의사항을 전송하는데 오류가 발생했습니다")
            }
        })

        profileViewModel.eventSetInquireSuccess.observe(this,{
            mainViewModel.setActionView(true)
            this.findNavController().popBackStack()
        })
    }

    fun clickUploadBtn(view: View) {
        binding.uploadBtn.isEnabled = false
        binding.loadingBar.settingVisibility(true)
        profileViewModel.setInquire(
            Inquire(
                mainViewModel.eventGetUserInfo.value!!.userId,
                binding.title.text.toString(),
                binding.content.text.toString(),
                nowTime()
            )
        )
    }

    private fun nowTime() = SimpleDateFormat("yyyy.MM.dd HH시 mm분", Locale("ko", "KR")).format(
        Date(System.currentTimeMillis())
    )
}