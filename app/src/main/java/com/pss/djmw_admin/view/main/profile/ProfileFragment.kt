package com.pss.djmw_admin.view.main.profile

import androidx.fragment.app.activityViewModels
import com.pss.djmw_admin.base.BaseFragment
import com.pss.djmw_admin.viewmodel.MainViewModel
import android.animation.ValueAnimator
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.pss.djmw_admin.R
import com.pss.djmw_admin.databinding.FragmentProfileBinding


class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun init() {
        binding.fragment = this
        initText()
    }

    fun clickAppGuideBtn(view: View) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://dolomite-sceptre-401.notion.site/296fcd6b77984184973451b682fa27ae")
            )
        )
    }

    fun clickUserInquireBtn(view: View){
        mainViewModel.setActionView(false)
        this.findNavController().navigate(R.id.action_profileFragment_to_setInquireFragment)
    }

    private fun initText() {
        binding.apply {
            mainViewModel.eventGetUserInfo.value?.apply {
                //참여한 문제 개수 카운트
                userInfoCountAnimation(
                    participationQuestion.toString().toInt(),
                    binding.participationQuestionScore
                )

                //정답 문제 개수 카운트
                userInfoCountAnimation(
                    answerQuestion.toString().toInt(),
                    binding.answerQuestionScore
                )

                //랭킹 등수 카운트
                userInfoCountAnimation(
                    mainViewModel.eventUserRankingInfo.value.toString().toInt(),
                    binding.rankingScore
                )
                userNiceName.text = userName
            }
        }
    }

    private fun userInfoCountAnimation(count: Int, txtView: TextView) {
        val animator = ValueAnimator.ofInt(0, count)
        animator.duration = 1500
        animator.addUpdateListener { animation ->
            txtView.text = animation.animatedValue.toString()
        }
        animator.start()
    }
}