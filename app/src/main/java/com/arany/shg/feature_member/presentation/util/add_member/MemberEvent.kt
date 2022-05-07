package com.arany.shg.feature_member.presentation.util.add_member

import com.arany.shg.feature_member.domain.util.RoleType

sealed class MemberEvent{
    data class EnteredName(val name: String): MemberEvent()
    data class EnteredAddress(val address: String): MemberEvent()
    data class EnteredPhoneNumber(val phoneNumber: String): MemberEvent()
    data class EnteredEmail(val email: String): MemberEvent()
    data class EnteredRole(val role: RoleType): MemberEvent()
}
