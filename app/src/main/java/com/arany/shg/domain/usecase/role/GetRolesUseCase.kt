package com.arany.shg.domain.usecase.role

import com.arany.shg.feature_role.domain.repository.RoleRepository

class GetRolesUseCase(private val roleRepository: RoleRepository) {
    suspend operator fun invoke() = roleRepository.getRoles()
}