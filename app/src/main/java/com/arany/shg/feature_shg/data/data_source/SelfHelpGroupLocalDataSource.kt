package com.arany.shg.feature_shg.data.data_source

import com.arany.shg.feature_shg.data.model.SelfHelpGroup
import kotlinx.coroutines.flow.Flow

interface SelfHelpGroupLocalDataSource {
    suspend fun saveSelfHelpGroupToDB(selfHelpGroup: SelfHelpGroup): Long?
    suspend fun getAllSelfHelpGroups(): List<SelfHelpGroup>
    suspend fun getSelfHelpGroupById(shgId: Int): SelfHelpGroup?
    suspend fun deleteSelfHelpGroup(selfHelpGroup: SelfHelpGroup)


}