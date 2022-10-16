package com.arany.shg.feature_role.domain.repository

import com.arany.shg.data.models.Role
import com.arany.shg.data.util.Resource

interface RoleRepository{
      suspend fun insertRole(role: Role)
      suspend fun updateRole(role: Role)
      suspend fun deleteRole(role: Role)
      suspend fun getRoles(): Resource<List<Role>>
      suspend fun getRoleById(roleId: Int): Resource<Role>
}