package com.pss.djmw_admin.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserParticipationInfo(
    //대답하기
    val question: ParticipationItem,

    //선택하기
    val answer : ParticipationItem
) : Parcelable {
    constructor() : this(ParticipationItem(false,false,false,false,false), ParticipationItem(false,false,false,false,false))
}