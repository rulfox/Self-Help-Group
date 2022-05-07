package com.arany.shg.feature_member.domain.util

sealed class RoleNameType(private val title: String) {
    object Member: RoleNameType("Member");
    object Secretary: RoleNameType("Secretary");
    object President: RoleNameType("President");
    object Lender: RoleNameType("Lender");
    fun getRoleTitle() = title
}