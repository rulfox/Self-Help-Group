package com.arany.shg.domain.usecase.role

import com.arany.shg.feature_fine.data.models.Fine
import com.arany.shg.feature_fine.domain.repository.FineRepository

class UpdateRoleUseCase(private val fineRepository: FineRepository) {
    suspend fun execute(fine: Fine) = fineRepository.updateFine(fine)
}