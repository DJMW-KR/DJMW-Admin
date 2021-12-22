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

    //서버 점검중 표시 (관리자 권한)
    fun setServerInspection() =  firebaseDatabase.reference.child("version").setValue("0.0.0")

    //서버 버전 변경 (관리자 권한)
    fun setVersionSave(version : String) =  firebaseDatabase.reference.child("version").setValue(version)
}