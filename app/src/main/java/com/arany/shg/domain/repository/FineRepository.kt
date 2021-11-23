package com.arany.shg.domain.repository

import com.arany.shg.data.models.Fine
import com.arany.shg.data.util.Resource

interface FineRepository{
      suspend fun createFine(fine: Fine)
      suspend fun getFinesOfCommittee(committeeId: Int): Resource<ArrayList<Fine>>
      suspend fun getFine(fineId: Int): Resource<Fine>
      suspend fun updateFine(fine: Fine)
      suspend fun deleteFine(fine: Fine)
}