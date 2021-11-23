package com.arany.shg.domain.repository

import com.arany.shg.data.util.Resource
import com.arany.shg.data.models.SelfHelpGroup
import kotlinx.coroutines.flow.Flow

interface SelfHelpGroupRepository{
      suspend fun createSelfHelpGroup(selfHelpGroup: SelfHelpGroup)
      suspend fun getSelfHelpGroupById(shgId: Int): Resource<SelfHelpGroup>
      suspend fun getAllSelfHelpGroups(): Flow<List<SelfHelpGroup>>
}