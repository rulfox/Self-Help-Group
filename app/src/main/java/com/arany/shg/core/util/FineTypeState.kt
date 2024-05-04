package com.arany.shg.core.util

import com.arany.shg.feature_fine.data.models.FineType
import com.arany.shg.feature_member.data.model.Member

data class FineTypeState (
    val hint: String = "",
    var fineType: FineType ?= null,
    var enabled: Boolean = false,
    var listOfFineTypes: List<FineType> = arrayListOf(),
)