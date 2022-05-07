package com.arany.shg.feature_member.domain.util

sealed class RoleType(val id: RoleIdType, private val title: RoleNameType){
    object President: RoleType(RoleIdType.President, RoleNameType.President)
    object Secretary: RoleType(RoleIdType.Secretary, RoleNameType.Secretary)
    object Lender: RoleType(RoleIdType.Lender, RoleNameType.Lender)
    object Member: RoleType(RoleIdType.Member, RoleNameType.Member)
    fun getRoleTitle() = title.getRoleTitle()
}
