package com.arany.shg.domain.usecase.member

import com.arany.shg.domain.repository.CommitteeRepository

class GetCommitteeUseCase(private val committeeRepository: CommitteeRepository) {
    suspend fun execute(committeeId: Int) = committeeRepository.getCommittee(committeeId)
}