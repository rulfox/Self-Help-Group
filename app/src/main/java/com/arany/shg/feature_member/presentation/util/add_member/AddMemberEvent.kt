package com.arany.shg.feature_member.presentation.util.add_member

import com.arany.shg.data.models.Committee
import com.arany.shg.data.models.Role
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_member.domain.util.RoleType

sealed class AddMemberEvent{
    data class EnteredName(val name: String): AddMemberEvent()
    data class EnteredAddress(val address: String): AddMemberEvent()
    data class EnteredPhoneNumber(val phoneNumber: String): AddMemberEvent()
    data class EnteredEmail(val email: String): AddMemberEvent()
    data class SelectedRole(val role: Role): AddMemberEvent()
    data class EnteredPassword(val password: String): AddMemberEvent()
    object AddMember: AddMemberEvent()
}
