package com.arany.shg.core.util

import com.arany.shg.feature_member.data.model.Member

data class MemberState (
    val hint: String = "",
    var member: Member ?= null,
    var enabled: Boolean = false,
    var listOfMembers: List<Member> = arrayListOf(),
)