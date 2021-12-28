package com.pss.djmw_admin.view.main.admin


import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.api.LogDescriptor
import com.pss.djmw_admin.R
import com.pss.djmw_admin.base.BaseFragment
import com.pss.djmw_admin.data.model.Question
import com.pss.djmw_admin.databinding.FragmentQuestionUploadBinding
import com.pss.djmw_admin.view.main.question.adapter.Sex

import com.pss.djmw_admin.viewmodel.AdminViewModel
import com.pss.djmw_admin.viewmodel.MainViewModel


class QuestionUploadFragment :
    BaseFragment<FragmentQuestionUploadBinding>(R.layout.fragment_question_upload) {
    //관리자 게시물 업로드 시 만들어야하는 것들
    //-fireStore-
    //1. 먼저 있던 질문들을 모두 삭제
    //man_question
    //woman_question
    //2. 사용자 스코어 통계 초기화 (관리자 기능 사용자 참여 통계 초기화)

    //-rtdb-
    //3. 아래 나오는 rtdb 관련 모든 내용들 다 초기화
    //man_question
    //man_answer
    //woman_question
    //woman_answer

    //4. 업로드 하기
    private val adminViewModel by activityViewModels<AdminViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun init() {
        binding.fragment = this
        observeViewModel()
    }

    private fun observeViewModel() {
        adminViewModel.eventNumber.observe(this, {
            Log.d("로그","가져온 질문 number : $it")
            val manQuestion = Question(
                binding.manAnswerOne.text.toString(),
                binding.manAnswerTwo.text.toString(),
                binding.manAnswerThree.text.toString(),
                binding.manAnswerFour.text.toString(),
                binding.manAnswerFive.text.toString(),
                binding.questionTitle.text.toString(),
                it
            )
            val womanQuestion = Question(
                binding.womanAnswerOne.text.toString(),
                binding.womanAnswerTwo.text.toString(),
                binding.womanAnswerThree.text.toString(),
                binding.womanAnswerFour.text.toString(),
                binding.womanAnswerFive.text.toString(),
                binding.questionTitle.text.toString(),
                it
            )
            adminViewModel.setQuestion(binding.questionTitle.text.toString(), manQuestion, womanQuestion)
            cleanTxt()
        })

        adminViewModel.eventError.observe(this, {
            when (it) {
                2 -> shortShowToast("number 가져오기에 실패했습니다")
            }
        })
    }

    private fun cleanTxt(){
        binding.manAnswerOne.setText("")
        binding.manAnswerTwo.setText("")
        binding.manAnswerThree.setText("")
        binding.manAnswerFour.setText("")
        binding.manAnswerFive.setText("")
        binding.womanAnswerOne.setText("")
        binding.womanAnswerTwo.setText("")
        binding.womanAnswerThree.setText("")
        binding.womanAnswerFour.setText("")
        binding.womanAnswerFive.setText("")
    }

    fun clickQuestionDelete(view: View) {
        adminViewModel.questionDelete()
    }

    fun clickQuestionStatisticsDelete(view: View) {
        adminViewModel.questionStatisticsDelete()
    }

    fun clickBackBtn(view: View) {
        mainViewModel.setActionView(true)
        this.findNavController().popBackStack()
    }

    fun clickQuestionUploadBtn(view: View) {
        adminViewModel.getNumber()
    }
}