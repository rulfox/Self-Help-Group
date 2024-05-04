package com.arany.shg.feature_fine.domain.repository

import com.arany.shg.feature_fine.data.models.Fine
import com.arany.shg.data.util.Resource
import com.arany.shg.feature_fine.data.models.FineWithDetails

interface FineRepository{
      suspend fun addFine(fine: Fine): Long
      suspend fun getFinesFromCommittee(committeeId: Int): Resource<List<Fine>>
      suspend fun getFinesFromCommitteeWithDetails(committeeId: Int): Resource<List<FineWithDetails>>
      suspend fun getFine(fineId: Int): Resource<Fine>
      suspend fun updateFine(fine: Fine): Int
      suspend fun deleteFine(fine: Fine): Int
}