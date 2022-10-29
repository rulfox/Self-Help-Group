package com.arany.shg.feature_shg.data.data_source

import com.arany.shg.data.db.SelfHelpGroupDAO
import com.arany.shg.feature_shg.data.model.SelfHelpGroup

class SelfHelpGroupLocalDataSourceImpl(private val selfHelpGroupDAO: SelfHelpGroupDAO) :
    SelfHelpGroupLocalDataSource {

    override suspend fun saveSelfHelpGroupToDB(selfHelpGroup: SelfHelpGroup): Long? {
        return selfHelpGroupDAO.insert(selfHelpGroup)
    }

    override suspend fun getAllSelfHelpGroups(): List<SelfHelpGroup> {
        return selfHelpGroupDAO.getAllSelfHelpGroups()
    }

    override suspend fun deleteSelfHelpGroup(selfHelpGroup: SelfHelpGroup) {
        selfHelpGroupDAO.deleteSelfHelpGroup(selfHelpGroup)
    }

    override suspend fun getSelfHelpGroupById(shgId: Int): SelfHelpGroup = selfHelpGroupDAO.getSelfHelpGroupById(shgId)
}