package com.arany.shg.feature_role.presentation

import com.arany.shg.data.models.Committee
import com.arany.shg.feature_member.data.model.Member

sealed class AddRoleEvent{
    data class EnteredRole(val role: String): AddRoleEvent()
    data class SelectedWriteAccess(val canWrite: Boolean): AddRoleEvent()
    object AddRole : AddRoleEvent()
}
