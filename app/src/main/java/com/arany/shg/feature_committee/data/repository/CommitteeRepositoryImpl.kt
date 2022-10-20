package com.arany.shg.feature_committee.data.repository

import com.arany.shg.data.models.Committee
import com.arany.shg.data.util.Resource
import com.arany.shg.feature_committee.data.datasource.CommitteeLocalDataSource
import com.arany.shg.feature_committee.domain.repository.CommitteeRepository
import javax.inject.Inject

class CommitteeRepositoryImpl @Inject constructor(private val committeeLocalDataSource: CommitteeLocalDataSource) : CommitteeRepository {
    override suspend fun createCommittee(committee: Committee) {
        committeeLocalDataSource.createCommittee(committee)
    }

    override suspend fun updateCommittee(committee: Committee) {
        committeeLocalDataSource.updateCommittee(committee)
    }

    override suspend fun getCommittee(committeeId: Int): Resource<Committee> {
        return committeeLocalDataSource.getCommittee(committeeId)
    }

    override suspend fun getCommitteesOfSelfHelpGroup(shgId: Int): Resource<List<Committee>> {
        return committeeLocalDataSource.getCommitteesOfSelfHelpGroup(shgId)
    }
}