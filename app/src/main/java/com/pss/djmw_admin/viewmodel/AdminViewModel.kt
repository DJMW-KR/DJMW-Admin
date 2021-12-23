package com.pss.djmw_admin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pss.djmw_admin.repository.AdminRepository
import com.pss.djmw_admin.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdminViewModel  @Inject constructor(
    private val adminRepository: AdminRepository
) : ViewModel() {
    val eventAdminPasswordCheck: LiveData<Boolean> get() = _eventAdminPasswordCheck
    private val _eventAdminPasswordCheck = SingleLiveEvent<Boolean>()

    val eventAdminAction: LiveData<Boolean> get() = _eventAdminAction
    private val _eventAdminAction = SingleLiveEvent<Boolean>()

    //0 = 인증번호 틀렸을때, 1 = 유저 통계 정보 전체 초기화 실패 시
    val eventError: LiveData<Int> get() = _eventError
    private val _eventError = SingleLiveEvent<Int>()


    fun adminPasswordCheck(password : String) = adminRepository.adminPasswordCheck(password)
        .addOnSuccessListener {
            if (password == it.value) _eventAdminPasswordCheck.postValue(true)
            else _eventAdminPasswordCheck.postValue(false)
        }
        .addOnFailureListener {
            _eventError.call()
        }

    fun setCleanUserParticipationInfo() = adminRepository.setCleanUserParticipationInfo()
        .addOnSuccessListener {
            _eventAdminAction.call()
        }
        .addOnFailureListener {
            _eventError.call()
        }

    fun setServerInspection() = adminRepository.setServerInspection()

    fun setVersionSave(version:String) = adminRepository.setVersionSave(version)
}