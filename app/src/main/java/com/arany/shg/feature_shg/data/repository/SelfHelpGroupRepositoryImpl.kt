package com.arany.shg.feature_shg.data.repository

import com.arany.shg.feature_shg.data.model.SelfHelpGroup
import com.arany.shg.data.util.Resource
import com.arany.shg.feature_shg.domain.repository.SelfHelpGroupRepository
import com.arany.shg.feature_shg.data.data_source.SelfHelpGroupLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SelfHelpGroupRepositoryImpl @Inject constructor(private val selfHelpGroupLocalDataSource: SelfHelpGroupLocalDataSource) :
    SelfHelpGroupRepository {

    override suspend fun createSelfHelpGroup(selfHelpGroup: SelfHelpGroup): Long? {
        return selfHelpGroupLocalDataSource.saveSelfHelpGroupToDB(selfHelpGroup)
    }

    override suspend fun getSelfHelpGroupById(shgId: Int): Resource<SelfHelpGroup> {
        return selfHelpGroupLocalDataSource.getSelfHelpGroupById(shgId)
            ?.let { Resource.Success(it) } ?: Resource.Error("Unable to find shg with id $shgId")
    }

    override suspend fun getAllSelfHelpGroups(): Flow<List<SelfHelpGroup>> {
        return selfHelpGroupLocalDataSource.getAllSelfHelpGroups()
    }
}