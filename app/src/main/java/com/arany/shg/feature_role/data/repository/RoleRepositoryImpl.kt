package com.arany.shg.feature_role.data.repository

import com.arany.shg.data.models.Role
import com.arany.shg.data.util.Resource
import com.arany.shg.feature_role.data.datasource.RoleLocalDataSource
import com.arany.shg.feature_role.domain.repository.RoleRepository
import javax.inject.Inject

class RoleRepositoryImpl@Inject constructor(private val roleLocalDataSource: RoleLocalDataSource): RoleRepository {
    override suspend fun insertRole(role: Role) {
        roleLocalDataSource.addRole(role)
    }

    override suspend fun updateRole(role: Role) {
        roleLocalDataSource.updateRole(role)
    }

    override suspend fun deleteRole(role: Role) {
        roleLocalDataSource.deleteRole(role)
    }

    override suspend fun getRoles(): Resource<List<Role>> {
        return Resource.Success(roleLocalDataSource.getRoles())
    }

    override suspend fun getRoleById(roleId: Int): Resource<Role> {
        return roleLocalDataSource.getRoleById(roleId)?.let { Resource.Success(it) } ?: Resource.Error("Failed to find Role")
    }
}