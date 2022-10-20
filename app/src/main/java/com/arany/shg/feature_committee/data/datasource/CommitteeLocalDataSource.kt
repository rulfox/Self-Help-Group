package com.arany.shg.feature_committee.data.datasource

import com.arany.shg.data.models.Committee
import com.arany.shg.data.util.Resource

interface CommitteeLocalDataSource {
    suspend fun createCommittee(committee: Committee)
    suspend fun updateCommittee(committee: Committee)
    suspend fun getCommittee(committeeId: Int): Resource<Committee>
    suspend fun getCommitteesOfSelfHelpGroup(shgId: Int): Resource<List<Committee>>
}