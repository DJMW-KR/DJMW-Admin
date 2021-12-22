package com.pss.djmw_admin.view.main.question

import androidx.fragment.app.activityViewModels
import com.pss.djmw_admin.R
import com.pss.djmw_admin.base.BaseFragment
import com.pss.djmw_admin.databinding.FragmentAnswerMeBinding
import com.pss.djmw_admin.view.main.question.adapter.AnswerRecyclerViewAdapter
import com.pss.djmw_admin.view.main.question.adapter.Sex
import com.pss.djmw_admin.view.main.question.adapter.State
import com.pss.djmw_admin.viewmodel.MainViewModel
import com.pss.djmw_admin.widget.extension.showVertical


// 선택하기
class AnswerMeFragment : BaseFragment<FragmentAnswerMeBinding>(R.layout.fragment_answer_me){
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun init() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.answerMeRecyclerView.showVertical(requireContext())
        binding.answerMeRecyclerView.adapter = AnswerRecyclerViewAdapter(
            mainViewModel,
            this,
            State.ANSWERME,
            checkSex()
        )
    }

    private fun checkSex() =
        if (mainViewModel.eventGetUserInfo.value?.sex == "man") Sex.MAN else Sex.WOMAN
}