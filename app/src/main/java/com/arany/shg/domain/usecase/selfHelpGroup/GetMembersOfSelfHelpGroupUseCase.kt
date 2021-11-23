package com.arany.shg.domain.usecase.selfHelpGroup

import com.arany.shg.domain.repository.MemberRepository

class GetMembersOfSelfHelpGroupUseCase(private val memberRepository: MemberRepository) {
    suspend fun execute(shgId: Int) = memberRepository.getMembersOfSelfHelpGroup(shgId)
}