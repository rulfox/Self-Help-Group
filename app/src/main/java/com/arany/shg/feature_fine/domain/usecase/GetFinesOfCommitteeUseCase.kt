package com.arany.shg.feature_fine.domain.usecase

import com.arany.shg.feature_fine.domain.repository.FineRepository

class GetFinesOfCommitteeUseCase(private val fineRepository: FineRepository) {
    suspend fun execute(committeeId: Int) = fineRepository.getFinesOfCommittee(committeeId)
}