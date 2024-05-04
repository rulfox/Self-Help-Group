package com.arany.shg.feature_fine.data.datasource

import com.arany.shg.data.db.FineDAO
import com.arany.shg.feature_fine.data.models.Fine
import com.arany.shg.feature_fine.data.models.FineWithDetails
import javax.inject.Inject

class FineLocalDataSourceImpl @Inject constructor(private val fineDao: FineDAO): FineLocalDataSource {
    override suspend fun addFine(Fine: Fine): Long {
        return fineDao.addFine(Fine)
    }

    override suspend fun getFinesFromCommittee(committeeId: Int): List<Fine> {
        return fineDao.getFinesFromCommittee(committeeId)
    }

    override suspend fun getFinesFromCommitteeWithDetails(committeeId: Int): List<FineWithDetails> {
        return fineDao.getFinesFromCommitteeWithDetails(committeeId)
    }

    override suspend fun getFinesFromSelfHelpGroup(shgId: Int): List<FineWithDetails> {
        return fineDao.getFines(shgId)
    }

    override suspend fun getFine(FineId: Int): Fine? {
        return fineDao.getFine(FineId)
    }

    override suspend fun updateFine(Fine: Fine): Int {
        return fineDao.updateFine(Fine)
    }

    override suspend fun deleteFine(Fine: Fine): Int {
        return fineDao.deleteFine(Fine)
    }
}