package com.arany.shg.feature_shg.domain.use_case

import com.arany.shg.feature_member.domain.repository.MemberRepository

class GetMembersOfSelfHelpGroupUseCase(private val memberRepository: MemberRepository) {
    suspend fun execute(shgId: Int) = memberRepository.getMembersOfSelfHelpGroup(shgId)
}