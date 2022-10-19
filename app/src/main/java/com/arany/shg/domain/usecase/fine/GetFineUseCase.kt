package com.arany.shg.domain.usecase.fine

import com.arany.shg.domain.repository.FineRepository

class GetFineUseCase(private val fineRepository: FineRepository) {
    suspend fun execute(fineId: Int) = fineRepository.getFine(fineId)
}