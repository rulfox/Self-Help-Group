package com.arany.shg.domain.usecase.member

import com.arany.shg.feature_committee.domain.repository.CommitteeRepository

class GetCommitteesOfSelfHelpGroupUseCase(private val committeeRepository: CommitteeRepository) {
    suspend fun execute(shgId: Int) = committeeRepository.getCommitteesOfSelfHelpGroup(shgId)
}