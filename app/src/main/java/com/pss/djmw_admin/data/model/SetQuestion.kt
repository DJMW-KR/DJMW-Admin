package com.pss.djmw_admin.data.model

data class SetQuestion(
    var answer_one: Int,
    var answer_two: Int,
    var answer_three: Int,
    var answer_four: Int,
    var answer_five: Int
) {
    constructor() : this(0, 0, 0, 0, 0)
}