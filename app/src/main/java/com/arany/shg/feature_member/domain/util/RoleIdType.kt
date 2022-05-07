package com.arany.shg.feature_member.domain.util

sealed class RoleIdType(private val id: Int){
    object Member: RoleIdType(0);
    object Secretary: RoleIdType(1);
    object President: RoleIdType(2);
    object Lender: RoleIdType(3);
    fun getRoleId() = id
}
