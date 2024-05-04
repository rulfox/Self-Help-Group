package com.arany.shg.core.util

import com.arany.shg.feature_member.data.model.Member

data class SpinnerState<T> (
    val hint: String = "",
    var info: T ?= null,
    var enabled: Boolean = false,
    var infoList: List<T> = arrayListOf(),
)