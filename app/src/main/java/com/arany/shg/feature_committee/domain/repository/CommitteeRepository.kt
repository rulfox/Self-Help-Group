package com.arany.shg.feature_committee.domain.repository

import com.arany.shg.data.models.Committee
import com.arany.shg.data.util.Resource
import com.arany.shg.feature_committee.data.model.CommitteeWithDetails

interface CommitteeRepository{
      suspend fun createCommittee(committee: Committee): Long
      suspend fun updateCommittee(committee: Committee)
      suspend fun getCommittee(committeeId: Int): Resource<Committee>
      suspend fun getCommitteesOfSelfHelpGroup(shgId: Int): Resource<List<Committee>>
      suspend fun getCommitteesOfSelfHelpGroupWithDetails(shgId: Int): Resource<List<CommitteeWithDetails>>
}