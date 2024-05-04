package com.arany.shg.feature_fine.domain.usecase

import com.arany.shg.feature_fine.domain.repository.FineRepository

class GetFinesFromCommitteeUseCase(private val fineRepository: FineRepository) {
    suspend fun execute(committeeId: Int) = fineRepository.getFinesFromCommittee(committeeId)
}