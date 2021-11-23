package com.arany.shg.domain.usecase.committee

import com.arany.shg.data.models.Committee
import com.arany.shg.domain.repository.CommitteeRepository

class CreateCommitteeUseCase(private val committeeRepository: CommitteeRepository) {
    suspend fun execute(committee: Committee) = committeeRepository.createCommittee(committee)
}