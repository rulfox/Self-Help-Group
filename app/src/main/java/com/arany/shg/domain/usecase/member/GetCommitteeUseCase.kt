package com.arany.shg.domain.usecase.member

import com.arany.shg.feature_committee.domain.repository.CommitteeRepository

class GetCommitteeUseCase(private val committeeRepository: CommitteeRepository) {
    suspend fun execute(committeeId: Int) = committeeRepository.getCommittee(committeeId)
}