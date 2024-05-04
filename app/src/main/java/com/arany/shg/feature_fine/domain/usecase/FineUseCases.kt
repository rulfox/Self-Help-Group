package com.arany.shg.feature_fine.domain.usecase

import javax.inject.Inject

data class FineUseCases @Inject constructor(
    val addFineUseCase: CreateFineUseCase,
    val deleteFineUseCase: DeleteFineUseCase,
    val getFinesFromCommitteeUseCase: GetFinesFromCommitteeUseCase,
    val getFineUseCase: GetFineUseCase,
    val updateFineUseCase: UpdateFineUseCase
)