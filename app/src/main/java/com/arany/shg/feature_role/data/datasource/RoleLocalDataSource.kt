package com.arany.shg.feature_role.data.datasource

import com.arany.shg.data.models.Role

interface RoleLocalDataSource {
    suspend fun addRole(role: Role): Long
    suspend fun updateRole(role: Role)
    suspend fun getRoles(): List<Role>
    suspend fun getRoleById(roleId: Int): Role?
    suspend fun deleteRole(role: Role)
}