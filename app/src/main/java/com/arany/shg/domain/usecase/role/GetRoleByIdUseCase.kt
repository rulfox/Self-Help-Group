package com.arany.shg.domain.usecase.role

import com.arany.shg.feature_role.domain.repository.RoleRepository

class GetRoleByIdUseCase(private val roleRepository: RoleRepository) {
    suspend operator fun invoke(roleId: Int) = roleRepository.getRoleById(roleId)
}