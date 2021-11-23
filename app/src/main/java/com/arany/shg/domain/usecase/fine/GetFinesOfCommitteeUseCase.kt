package com.arany.shg.domain.usecase.fine

import com.arany.shg.data.*
import com.arany.shg.domain.repository.*

class GetFinesOfCommitteeUseCase(private val fineRepository: FineRepository) {
    suspend fun execute(committeeId: Int) = fineRepository.getFinesOfCommittee(committeeId)
}