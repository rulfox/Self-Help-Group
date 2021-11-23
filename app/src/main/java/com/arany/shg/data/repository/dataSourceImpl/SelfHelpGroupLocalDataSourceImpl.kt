package com.arany.shg.data.repository.dataSourceImpl

import com.arany.shg.data.db.SelfHelpGroupDAO
import com.arany.shg.data.models.SelfHelpGroup
import com.arany.shg.data.repository.dataSource.SelfHelpGroupLocalDataSource
import kotlinx.coroutines.flow.Flow

class SelfHelpGroupLocalDataSourceImpl(private val selfHelpGroupDAO: SelfHelpGroupDAO) :
    SelfHelpGroupLocalDataSource {

    override suspend fun saveSelfHelpGroupToDB(selfHelpGroup: SelfHelpGroup) {
        selfHelpGroupDAO.insert(selfHelpGroup)
    }

    override suspend fun deleteSelfHelpGroup(selfHelpGroup: SelfHelpGroup) {
        selfHelpGroupDAO.deleteSelfHelpGroup(selfHelpGroup)
    }

    override fun getAllSelfHelpGroups(): Flow<List<SelfHelpGroup>> = selfHelpGroupDAO.getAllSelfHelpGroups()

    override suspend fun getSelfHelpGroupById(shgId: Int): SelfHelpGroup = selfHelpGroupDAO.getSelfHelpGroupById(shgId)
}