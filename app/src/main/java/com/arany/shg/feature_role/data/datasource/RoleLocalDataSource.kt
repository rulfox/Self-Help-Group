package com.arany.shg.feature_role.data.datasource

import com.arany.shg.data.models.Role
import com.arany.shg.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface RoleLocalDataSource {
    suspend fun addRole(role: Role): Long
    suspend fun updateRole(role: Role)
    suspend fun getRoles(): List<Role>
    suspend fun getRoleById(roleId: Int): Role?
    suspend fun deleteRole(role: Role)
}