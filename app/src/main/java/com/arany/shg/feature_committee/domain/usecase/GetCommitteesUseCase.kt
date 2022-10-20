package com.arany.shg.feature_committee.domain.usecase

import com.arany.shg.feature_committee.domain.repository.CommitteeRepository

class GetCommitteesUseCase(private val memberRepository: CommitteeRepository) {
    suspend operator fun invoke(shgId: Int) = memberRepository.getCommitteesOfSelfHelpGroup(shgId)
}