package com.arany.shg.feature_member.domain.use_case

import javax.inject.Inject

data class MemberUseCases @Inject constructor(
    val createMemberUseCase: CreateMemberUseCase,
    val getMemberUseCase: GetMemberUseCase,
    val getMembersByShgIdUseCase: GetMembersByShgIdUseCase
)
