package com.pss.djmw_admin.repository

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class AdminRepository @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firestore: FirebaseFirestore
) {
    //어드민 비밀번호 체크
    fun adminPasswordCheck(password : String) = firebaseDatabase.reference.child("admin").get()

    //사용자 정보 초기화 (관리자 권한)
    fun setCleanUserParticipationInfo() =  firebaseDatabase.reference.child("userParticipationInfo").setValue("null")
}