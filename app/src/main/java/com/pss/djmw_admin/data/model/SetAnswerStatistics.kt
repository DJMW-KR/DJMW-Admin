package com.pss.djmw_admin.data.model

data class SetAnswerStatistics(
    var statistics_one : Int,
    var statistics_two : Int,
    var statistics_three : Int,
    var statistics_four : Int,
    var statistics_five : Int
){
    constructor() :this(0,0,0,0,0)
}
