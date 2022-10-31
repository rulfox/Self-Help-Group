package com.arany.shg.feature_fine.domain.usecase

import com.arany.shg.feature_fine.data.models.Fine
import com.arany.shg.feature_fine.domain.repository.FineRepository

class UpdateFineUseCase(private val fineRepository: FineRepository) {
    suspend fun execute(fine: Fine) = fineRepository.updateFine(fine)
}