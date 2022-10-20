package com.arany.shg.feature_committee.domain.usecase

import com.arany.shg.data.models.Committee
import com.arany.shg.feature_committee.domain.repository.CommitteeRepository

class CreateCommitteeUseCase(private val memberRepository: CommitteeRepository) {
    suspend operator fun invoke(committee: Committee) = memberRepository.createCommittee(committee)
}