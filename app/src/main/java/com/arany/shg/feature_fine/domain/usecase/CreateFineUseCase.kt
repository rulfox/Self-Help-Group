package com.arany.shg.feature_fine.domain.usecase

import com.arany.shg.feature_fine.data.models.Fine
import com.arany.shg.feature_fine.domain.repository.FineRepository

class CreateFineUseCase(private val fineRepository: FineRepository) {
    suspend fun execute(fine: Fine) = fineRepository.createFine(fine)
}