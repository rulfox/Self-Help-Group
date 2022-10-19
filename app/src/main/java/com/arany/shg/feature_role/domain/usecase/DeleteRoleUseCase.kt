package com.arany.shg.feature_role.domain.usecase

import com.arany.shg.data.models.Role
import com.arany.shg.feature_role.domain.repository.RoleRepository

class DeleteRoleUseCase(private val roleRepository: RoleRepository) {
    suspend operator fun invoke(role: Role) = roleRepository.deleteRole(role)
}