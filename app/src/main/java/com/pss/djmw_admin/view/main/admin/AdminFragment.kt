package com.pss.djmw_admin.view.main.admin

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.pss.djmw_admin.R
import com.pss.djmw_admin.base.BaseFragment
import com.pss.djmw_admin.databinding.FragmentAdminBinding
import com.pss.djmw_admin.viewmodel.AdminViewModel
import com.pss.djmw_admin.viewmodel.MainViewModel


class AdminFragment : BaseFragment<FragmentAdminBinding>(R.layout.fragment_admin) {
    private val adminViewModel by activityViewModels<AdminViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun init() {
        binding.fragment = this
        observeViewModel()
    }

    fun clickAdminPasswordBtn(view: View) {
        adminViewModel.adminPasswordCheck(binding.passwordEditTxt.text.toString())
    }

    fun clickAdminOneBtn(view: View) {
        if (checkAdminAuth()) adminViewModel.setCleanUserParticipationInfo()
    }

    fun clickAdminTwoBtn(view: View) {
        if (checkAdminAuth()) {
            mainViewModel.setActionView(false)
            this.findNavController().navigate(R.id.action_adminFragment_to_serverVersionFragment)
        }
    }

    private fun checkAdminAuth() = if (adminViewModel.eventAdminPasswordCheck.value == true) true
    else {
        shortShowToast("관리자 권한을 활성화 해 주세요")
        false
    }


    private fun observeViewModel() {
        adminViewModel.eventAdminPasswordCheck.observe(this, {
            if (it) shortShowToast("권환이 활성화 되었습니다")
            else shortShowToast("관리자 권한 번호가 다릅니다")
        })

        adminViewModel.eventError.observe(this, {
            when (it) {
                0 -> shortShowToast("관리자 권환을 인증하는데 오류가 발생했습니다")
            }
        })
    }
}