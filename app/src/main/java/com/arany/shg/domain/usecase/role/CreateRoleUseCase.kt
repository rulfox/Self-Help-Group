package com.arany.shg.domain.usecase.role

import com.arany.shg.data.models.Role
import com.arany.shg.feature_role.domain.repository.RoleRepository

class CreateRoleUseCase(private val roleRepository: RoleRepository) {
    suspend operator fun invoke(role: Role) = roleRepository.insertRole(role)
}