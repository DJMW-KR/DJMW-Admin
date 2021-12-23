package com.pss.djmw_admin.view.main.admin

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.pss.djmw_admin.R
import com.pss.djmw_admin.base.BaseFragment
import com.pss.djmw_admin.databinding.FragmentServerVersionBinding
import com.pss.djmw_admin.viewmodel.AdminViewModel


class ServerVersionFragment :
    BaseFragment<FragmentServerVersionBinding>(R.layout.fragment_server_version) {
    private val adminViewModel by activityViewModels<AdminViewModel>()


    override fun init() {
        binding.fragment = this
        observeViewModel()
    }

    fun clickServerInspectionBtn(view: View) {
        adminViewModel.setServerInspection()
    }

    fun clickBackBtn(view: View){
        this.findNavController().popBackStack()
    }

    fun clickVersionSaveBtn(view: View) {
        if (TextUtils.isEmpty(binding.versionEditTxt.text.toString())) shortShowToast("버전을 입력하세요")
        else adminViewModel.setVersionSave(binding.versionEditTxt.text.toString())
    }

    private fun observeViewModel(){
        adminViewModel.eventAdminAction.observe(this,{
            shortShowToast("관리자 권한 실행이 성공했습니다")
        })

        adminViewModel.eventError.observe(this,{
            shortShowToast("관리자 권한 실행이 실패했습니다")
        })
    }
}