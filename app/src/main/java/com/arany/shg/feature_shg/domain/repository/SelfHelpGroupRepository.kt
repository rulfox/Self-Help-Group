package com.arany.shg.feature_shg.domain.repository

import com.arany.shg.data.util.Resource
import com.arany.shg.feature_shg.data.model.SelfHelpGroup
import kotlinx.coroutines.flow.Flow

interface SelfHelpGroupRepository{
      suspend fun createSelfHelpGroup(selfHelpGroup: SelfHelpGroup): Long?
      suspend fun getSelfHelpGroupById(shgId: Int): Resource<SelfHelpGroup>
      suspend fun getAllSelfHelpGroups(): Resource<List<SelfHelpGroup>>
}