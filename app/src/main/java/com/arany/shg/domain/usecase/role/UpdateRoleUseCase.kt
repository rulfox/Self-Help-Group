package com.arany.shg.domain.usecase.role

import com.arany.shg.data.models.Fine
import com.arany.shg.domain.repository.*

class UpdateRoleUseCase(private val fineRepository: FineRepository) {
    suspend fun execute(fine: Fine) = fineRepository.updateFine(fine)
}