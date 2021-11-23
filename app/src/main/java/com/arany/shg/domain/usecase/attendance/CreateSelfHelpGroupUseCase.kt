package com.arany.shg.domain.usecase.attendance

import com.arany.shg.data.models.SelfHelpGroup
import com.arany.shg.domain.repository.SelfHelpGroupRepository

class CreateSelfHelpGroupUseCase(private val selfHelpGroupRepository: SelfHelpGroupRepository) {
    suspend fun execute(selfHelpGroup: SelfHelpGroup) = selfHelpGroupRepository.createSelfHelpGroup(selfHelpGroup)
}