package com.pss.djmw_admin.view.main.question

import android.util.Log
import androidx.fragment.app.activityViewModels
import com.pss.djmw_admin.R
import com.pss.djmw_admin.base.BaseFragment
import com.pss.djmw_admin.databinding.FragmentAnswerBinding
import com.pss.djmw_admin.view.main.question.adapter.AnswerRecyclerViewAdapter
import com.pss.djmw_admin.view.main.question.adapter.Sex
import com.pss.djmw_admin.view.main.question.adapter.State
import com.pss.djmw_admin.viewmodel.MainViewModel
import com.pss.djmw_admin.widget.extension.showVertical

// 대답하기
class AnswerFragment : BaseFragment<FragmentAnswerBinding>(R.layout.fragment_answer) {
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun init() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        Log.d(
            "TAG",
            "AnswerFragment initRecyclerView 메서드 호출 : ${mainViewModel.manQuestionList.size}"
        )
        binding.answerRecyclerView.showVertical(requireContext())
        binding.answerRecyclerView.adapter =
            AnswerRecyclerViewAdapter(mainViewModel, this, State.ANSWER, checkSex())
    }

    private fun checkSex() =
        if (mainViewModel.eventGetUserInfo.value?.sex == "man") Sex.MAN else Sex.WOMAN
}