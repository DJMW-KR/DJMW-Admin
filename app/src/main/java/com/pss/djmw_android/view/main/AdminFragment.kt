package com.pss.djmw_android.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.pss.djmw_android.R
import com.pss.djmw_android.base.BaseFragment
import com.pss.djmw_android.databinding.FragmentAdminBinding
import com.pss.djmw_android.viewmodel.AdminViewModel


class AdminFragment : BaseFragment<FragmentAdminBinding>(R.layout.fragment_admin) {
    private val adminViewModel by activityViewModels<AdminViewModel>()


    override fun init() {
        binding.fragment = this
        observeViewModel()
    }

    fun clickAdminPasswordBtn(view: View){
        adminViewModel.adminPasswordCheck(binding.passwordEditTxt.text.toString())
    }

    private fun observeViewModel(){
        adminViewModel.eventAdminPasswordCheck.observe(this,{
            if (it) shortShowToast("권환이 활성화 되었습니다")
            else shortShowToast("관리자 권한 번호가 다릅니다")
        })

        adminViewModel.eventError.observe(this, {
            when(it){
                0 -> shortShowToast("관리자 권환을 인증하는데 오류가 발생했습니다")
            }
        })
    }
}