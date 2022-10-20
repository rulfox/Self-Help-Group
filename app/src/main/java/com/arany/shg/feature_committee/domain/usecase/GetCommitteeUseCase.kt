package com.arany.shg.feature_committee.domain.usecase

import com.arany.shg.feature_committee.domain.repository.CommitteeRepository

class GetCommitteeUseCase(private val memberRepository: CommitteeRepository) {
    suspend operator fun invoke(committeeId: Int) = memberRepository.getCommittee(committeeId = committeeId)
}