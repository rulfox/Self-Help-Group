package com.arany.shg.core.util

import com.arany.shg.data.models.Committee

data class CommitteeState (
    val hint: String = "",
    val committee: Committee ?= null,
    var enabled: Boolean = false,
    var listOfCommittees: List<Committee> = arrayListOf()
)