package com.pss.djmw_admin.view.main.question.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

object QuestionBindingAdapter {

    @JvmStatic
    @BindingAdapter("set_question")
    fun setQuestion(text: TextView, question: String) {
        text.text = question
    }

    @JvmStatic
    @BindingAdapter("set_people")
    fun setPeople(text: TextView, people: String) {
        text.text = "참여인원 : $people"
    }
}