package com.pss.djmw_admin.repository

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.pss.djmw_admin.data.model.Inquire
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firestore: FirebaseFirestore
) {
    //사용자 문의하기
    fun setInquire(inquire: Inquire) = firestore.collection("userInquire").document().set(inquire)
}