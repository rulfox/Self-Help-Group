package com.arany.shg.feature_shg.domain.use_case

import com.arany.shg.feature_shg.domain.repository.SelfHelpGroupRepository

class GetSelfHelpGroupByIdUseCase(private val selfHelpGroupRepository: SelfHelpGroupRepository) {
    suspend fun execute(shgId: Int) = selfHelpGroupRepository.getSelfHelpGroupById(shgId)
}