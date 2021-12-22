package com.pss.djmw_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pss.djmw_android.repository.AdminRepository
import com.pss.djmw_android.repository.MainRepository
import com.pss.djmw_android.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdminViewModel  @Inject constructor(
    private val adminRepository: AdminRepository
) : ViewModel() {
    val eventAdminPasswordCheck: LiveData<Boolean> get() = _eventAdminPasswordCheck
    private val _eventAdminPasswordCheck = SingleLiveEvent<Boolean>()

    val eventError: LiveData<Int> get() = _eventError
    private val _eventError = SingleLiveEvent<Int>()


    fun adminPasswordCheck(password : String) = adminRepository.adminPasswordCheck(password)
        .addOnSuccessListener {
            if (password == it.value) _eventAdminPasswordCheck.postValue(true)
            else _eventAdminPasswordCheck.postValue(false)
        }
        .addOnFailureListener {
            _eventError.postValue(0)
        }
}