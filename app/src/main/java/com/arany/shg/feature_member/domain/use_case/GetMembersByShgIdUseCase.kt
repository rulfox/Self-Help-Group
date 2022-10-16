package com.arany.shg.feature_member.domain.use_case

import com.arany.shg.feature_member.domain.repository.MemberRepository

class GetMembersByShgIdUseCase(private val memberRepository: MemberRepository) {
    suspend operator fun invoke(shgId: Int) = memberRepository.getMembersOfSelfHelpGroup(shgId)
}