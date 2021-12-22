package com.pss.djmw_admin.repository

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.pss.djmw_admin.data.model.UserInfo
import javax.inject.Inject

class SignInRepository @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firestore: FirebaseFirestore
) {
    fun setUserInfo(userInfo : UserInfo) = firestore.collection("userInfo").document(userInfo.userId).set(userInfo)
}