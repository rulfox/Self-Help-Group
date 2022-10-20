package com.arany.shg.feature_committee.data.datasource

import com.arany.shg.data.db.CommitteeDAO
import com.arany.shg.data.models.Committee
import com.arany.shg.data.util.Resource
import javax.inject.Inject

class CommitteeLocalDataSourceImpl @Inject constructor(private val committeeDao: CommitteeDAO): CommitteeLocalDataSource {
    override suspend fun createCommittee(committee: Committee) {
        committeeDao.addCommittee(committee)
    }

    override suspend fun updateCommittee(committee: Committee) {
        committeeDao.updateCommittee(committee)
    }

    override suspend fun getCommittee(committeeId: Int): Resource<Committee> {
        return committeeDao.getCommittee(committeeId)?.let { Resource.Success(it) } ?: Resource.Error("Unable to find committee with id $committeeId")
    }

    override suspend fun getCommitteesOfSelfHelpGroup(shgId: Int): Resource<List<Committee>> {
        return committeeDao.getCommitteesBySelfHelpGroupId(shgId).let { Resource.Success(it) }
    }
}