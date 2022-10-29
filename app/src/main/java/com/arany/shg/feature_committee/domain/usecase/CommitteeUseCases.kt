package com.arany.shg.feature_committee.domain.usecase

import javax.inject.Inject

data class CommitteeUseCases @Inject constructor(
    val createCommitteeUseCase: CreateCommitteeUseCase,
    val updateCommitteeUseCase: UpdateCommitteeUseCase,
    val deleteCommitteeUseCase: CreateCommitteeUseCase,
    val getCommitteeUseCase: GetCommitteeUseCase,
    val getCommitteesUseCase: GetCommitteesUseCase,
    val getCommitteesWithDetailsUseCase : GetCommitteesWithDetailsUseCase,
)