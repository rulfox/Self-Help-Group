package com.arany.shg.domain.usecase.fine

import com.arany.shg.domain.repository.FineRepository

class GetFinesOfCommitteeUseCase(private val fineRepository: FineRepository) {
    suspend fun execute(committeeId: Int) = fineRepository.getFinesOfCommittee(committeeId)
}