package com.arany.shg.feature_shg.data.data_source

import com.arany.shg.data.db.SelfHelpGroupDAO
import com.arany.shg.feature_shg.data.model.SelfHelpGroup
import com.arany.shg.feature_shg.data.data_source.SelfHelpGroupLocalDataSource
import kotlinx.coroutines.flow.Flow

class SelfHelpGroupLocalDataSourceImpl(private val selfHelpGroupDAO: SelfHelpGroupDAO) :
    SelfHelpGroupLocalDataSource {

    override suspend fun saveSelfHelpGroupToDB(selfHelpGroup: SelfHelpGroup): Long? {
        return selfHelpGroupDAO.insert(selfHelpGroup)
    }

    override suspend fun deleteSelfHelpGroup(selfHelpGroup: SelfHelpGroup) {
        selfHelpGroupDAO.deleteSelfHelpGroup(selfHelpGroup)
    }

    override fun getAllSelfHelpGroups(): Flow<List<SelfHelpGroup>> = selfHelpGroupDAO.getAllSelfHelpGroups()

    override suspend fun getSelfHelpGroupById(shgId: Int): SelfHelpGroup = selfHelpGroupDAO.getSelfHelpGroupById(shgId)
}