package com.arany.shg.feature_fine.domain.repository

import com.arany.shg.feature_fine.data.models.Fine
import com.arany.shg.data.util.Resource

interface FineRepository{
      suspend fun createFine(fine: Fine)
      suspend fun getFinesOfCommittee(committeeId: Int): Resource<ArrayList<Fine>>
      suspend fun getFine(fineId: Int): Resource<Fine>
      suspend fun updateFine(fine: Fine)
      suspend fun deleteFine(fine: Fine)
}