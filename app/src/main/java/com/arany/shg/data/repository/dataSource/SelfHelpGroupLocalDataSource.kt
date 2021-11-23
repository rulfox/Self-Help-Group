package com.arany.shg.data.repository.dataSource

import com.arany.shg.data.models.SelfHelpGroup
import kotlinx.coroutines.flow.Flow

interface SelfHelpGroupLocalDataSource {
    suspend fun saveSelfHelpGroupToDB(selfHelpGroup: SelfHelpGroup)
    fun getAllSelfHelpGroups(): Flow<List<SelfHelpGroup>>
    suspend fun getSelfHelpGroupById(shgId: Int): SelfHelpGroup?
    suspend fun deleteSelfHelpGroup(selfHelpGroup: SelfHelpGroup)


}