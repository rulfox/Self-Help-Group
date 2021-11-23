package com.arany.shg.domain.usecase.selfHelpGroup

import com.arany.shg.domain.repository.SelfHelpGroupRepository

class GetSelfHelpGroupByIdUseCase(private val selfHelpGroupRepository: SelfHelpGroupRepository) {
    suspend fun execute(shgId: Int) = selfHelpGroupRepository.getSelfHelpGroupById(shgId)
}