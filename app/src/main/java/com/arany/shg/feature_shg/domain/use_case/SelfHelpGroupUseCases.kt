package com.arany.shg.feature_shg.domain.use_case

data class SelfHelpGroupUseCases(
    val createSelfHelpGroupUseCase: CreateSelfHelpGroupUseCase,
    val getAllSelfHelpGroupsUseCase: GetAllSelfHelpGroupsUseCase,
    val getMembersOfSelfHelpGroupUseCase: GetMembersOfSelfHelpGroupUseCase,
    val getSelfHelpGroupByIdUseCase: GetSelfHelpGroupByIdUseCase
)
