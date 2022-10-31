package com.arany.shg.domain.usecase.role

import com.arany.shg.feature_fine.domain.repository.FineRepository

class GetFineUseCase(private val fineRepository: FineRepository) {
    suspend fun execute(fineId: Int) = fineRepository.getFine(fineId)
}