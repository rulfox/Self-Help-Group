package com.arany.shg.feature_role.data.datasource

import com.arany.shg.data.db.RoleDAO
import com.arany.shg.data.models.Role

class RoleLocalDataSourceImpl(private val roleDao: RoleDAO): RoleLocalDataSource {
    override suspend fun addRole(role: Role): Long = roleDao.addRole(role)

    override suspend fun updateRole(role: Role) = roleDao.updateRole(role)

    override suspend fun getRoles(): List<Role> = roleDao.getRoles()

    override suspend fun getRoleById(roleId: Int) = roleDao.getRoleById(roleId)

    override suspend fun deleteRole(role: Role) = roleDao.deleteRole(role)
}