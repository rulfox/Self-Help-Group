package com.arany.shg.domain.repository

import com.arany.shg.data.models.Committee
import com.arany.shg.data.util.Resource

interface CommitteeRepository{
      suspend fun createCommittee(committee: Committee)
      suspend fun getCommittee(committeeId: Int): Resource<Committee>
      suspend fun getCommitteesOfSelfHelpGroup(shgId: Int): Resource<ArrayList<Committee>>
}