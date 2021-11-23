package com.arany.shg.domain.usecase.selfHelpGroup

import com.arany.shg.domain.repository.SelfHelpGroupRepository

class GetAllSelfHelpGroupsUseCase(private val selfHelpGroupRepository: SelfHelpGroupRepository) {
    suspend fun execute() = selfHelpGroupRepository.getAllSelfHelpGroups()
}