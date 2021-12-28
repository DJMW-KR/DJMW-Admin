package com.pss.djmw_admin.repository

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject
import com.google.firebase.firestore.QueryDocumentSnapshot

import androidx.annotation.NonNull
import com.pss.djmw_admin.data.model.Question
import com.pss.djmw_admin.data.model.SetQuestion
import com.pss.djmw_admin.view.main.question.adapter.Sex


class AdminRepository @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firestore: FirebaseFirestore
) {
    //어드민 비밀번호 체크
    fun adminPasswordCheck(password: String) = firebaseDatabase.reference.child("admin").get()

    //사용자 정보 초기화 (관리자 권한)
    fun setCleanUserParticipationInfo() =
        firebaseDatabase.reference.child("userParticipationInfo").setValue("null")

    //서버 점검중 표시 (관리자 권한)
    fun setServerInspection() = firebaseDatabase.reference.child("version").setValue("0.0.0")

    //서버 버전 변경 (관리자 권한)
    fun setVersionSave(version: String) =
        firebaseDatabase.reference.child("version").setValue(version)

    //질문 전체 삭제
    fun questionDelete() {
        firestore.collection("man_question").get().addOnCompleteListener { task ->
            if (task.result!!.size() > 0) {
                for (dc in task.result!!) {
                    dc.reference.delete()
                }
            }
        }

        firestore.collection("woman_question").get().addOnCompleteListener { task ->
            if (task.result!!.size() > 0) {
                for (dc in task.result!!) {
                    dc.reference.delete()
                }
            }
        }
    }

    //질문 스코어 통계 초기화
    fun questionStatisticsDelete() {
        firebaseDatabase.reference.child("man_answer").setValue("null")
        firebaseDatabase.reference.child("man_question").setValue("null")
        firebaseDatabase.reference.child("woman_answer").setValue("null")
        firebaseDatabase.reference.child("woman_question").setValue("null")
    }

    //질문 등록
    fun setQuestion(title: String, content: Question, sex: Sex) {
        if (sex == Sex.MAN) firestore.collection("man_question").document(title).set(content)
        else firestore.collection("woman_question").document(title).set(content)
        setStatistics("man_answer",title)
        setStatistics("woman_answer",title)
        setStatistics("man_question",title)
        setStatistics("woman_question",title)
    }

    private fun setStatistics(child: String, title: String) = firebaseDatabase.reference.child(child).child(title).setValue(SetQuestion())

    //number 를 알기위해 게시물 개수 가져오기
    fun getNumber() = firestore.collection("man_question").get()
}