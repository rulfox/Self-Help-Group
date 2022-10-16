package com.arany.shg.feature_role.domain.usecase

import com.arany.shg.data.models.Role
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_member.domain.repository.MemberRepository
import com.arany.shg.feature_role.domain.repository.RoleRepository

class AddRoleUseCase(private val roleRepository: RoleRepository) {
    suspend operator fun invoke(role: Role) = roleRepository.insertRole(role)
}