package com.arany.shg.core.util

import com.arany.shg.data.models.Role

data class RoleState (
    val hint: String = "",
    val role: Role ?= null,
    var enabled: Boolean = false,
    var listOfRoles: List<Role> = arrayListOf()
)