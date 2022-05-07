package com.arany.shg.feature_shg.domain.use_case

import com.arany.shg.feature_onboarding.data.model.InvalidLoginException
import com.arany.shg.feature_onboarding.data.model.LoginRequest
import com.arany.shg.feature_shg.data.model.SelfHelpGroup
import com.arany.shg.feature_shg.domain.repository.SelfHelpGroupRepository
import com.arany.shg.feature_shg.domain.util.InvalidCreateSelfHelpGroupException

class CreateSelfHelpGroupUseCase(private val selfHelpGroupRepository: SelfHelpGroupRepository) {
    @Throws(InvalidCreateSelfHelpGroupException::class)
    suspend operator fun invoke(request: SelfHelpGroup): Long{
        if(request.name.isBlank()) throw InvalidLoginException("Please SHG name")
        if(request.address.isBlank()) throw InvalidLoginException("Please enter address")
        return selfHelpGroupRepository.createSelfHelpGroup(request) ?: throw InvalidCreateSelfHelpGroupException("Please enter valid credentials")
    }
}