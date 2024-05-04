package com.arany.shg.feature_fine.data.repository

import com.arany.shg.data.util.Resource
import com.arany.shg.feature_fine.data.datasource.FineLocalDataSource
import com.arany.shg.feature_fine.data.models.Fine
import com.arany.shg.feature_fine.data.models.FineWithDetails
import com.arany.shg.feature_fine.domain.repository.FineRepository
import com.arany.shg.feature_loan.data.datasource.LoanLocalDataSource
import javax.inject.Inject

class FineRepositoryImpl@Inject constructor(private val fineLocalDataSource: FineLocalDataSource): FineRepository {
    override suspend fun addFine(fine: Fine): Long {
        return fineLocalDataSource.addFine(fine)
    }

    override suspend fun getFinesFromCommittee(committeeId: Int): Resource<List<Fine>> {
        return Resource.Success(fineLocalDataSource.getFinesFromCommittee(committeeId))
    }

    override suspend fun getFinesFromCommitteeWithDetails(committeeId: Int): Resource<List<FineWithDetails>> {
        return Resource.Success(fineLocalDataSource.getFinesFromCommitteeWithDetails(committeeId))
    }

    override suspend fun getFine(fineId: Int): Resource<Fine> {
        fineLocalDataSource.getFine(fineId)?.let {
            return Resource.Success(it)
        }?: run {
            return Resource.Error("Fine not found")
        }
    }

    override suspend fun updateFine(fine: Fine): Int {
        return fineLocalDataSource.updateFine(fine)
    }

    override suspend fun deleteFine(fine: Fine): Int {
        return fineLocalDataSource.deleteFine(fine)
    }
}