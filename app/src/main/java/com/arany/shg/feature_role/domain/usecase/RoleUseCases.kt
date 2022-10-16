package com.arany.shg.feature_role.domain.usecase

import javax.inject.Inject

data class RoleUseCases @Inject constructor(
    val addRoleUseCase: AddRoleUseCase,
    val deleteRoleUseCase: DeleteRoleUseCase,
    val getRoleByIdUseCase: GetRoleByIdUseCase,
    val getRolesUseCase: GetRolesUseCase,
    val updateRoleUseCase: UpdateRoleUseCase
)
