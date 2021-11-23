package com.arany.shg.domain.usecase.fine

import com.arany.shg.data.models.Fine
import com.arany.shg.domain.repository.*

class UpdateFineUseCase(private val fineRepository: FineRepository) {
    suspend fun execute(fine: Fine) = fineRepository.updateFine(fine)
}