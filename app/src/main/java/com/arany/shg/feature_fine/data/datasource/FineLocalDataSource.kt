package com.arany.shg.feature_fine.data.datasource

import com.arany.shg.feature_fine.data.models.Fine
import com.arany.shg.feature_fine.data.models.FineWithDetails

interface FineLocalDataSource {
    suspend fun addFine(Fine: Fine): Long
    suspend fun getFinesFromCommittee(committeeId: Int): List<Fine>
    suspend fun getFinesFromCommitteeWithDetails(committeeId: Int): List<FineWithDetails>
    suspend fun getFinesFromSelfHelpGroup(shgId: Int): List<FineWithDetails>
    suspend fun getFine(FineId: Int): Fine?
    suspend fun updateFine(Fine: Fine): Int
    suspend fun deleteFine(Fine: Fine): Int
}