package com.pss.djmw_admin.repository

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.pss.djmw_admin.data.model.UserParticipationInfo
import javax.inject.Inject

class SplashRepository @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firestore: FirebaseFirestore
) {
    //앱 버전 체크
    fun versionCheck() = firebaseDatabase.reference.child("version").get()

    //사용자 퀴즈 참여 여부 통계정보 가져오기
    fun getUserParticipationInfo(id : String) = firebaseDatabase.reference.child("userParticipationInfo").child(id).get()

    //사용자 통계 정보 초기화 후 모두 false 로 설정
    fun setSettingUserParticipation(item : UserParticipationInfo, id: String) = firebaseDatabase.reference.child("userParticipationInfo").child(id).setValue(item)
}