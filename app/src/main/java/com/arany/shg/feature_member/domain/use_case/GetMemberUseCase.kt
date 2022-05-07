package com.arany.shg.feature_member.domain.use_case

import com.arany.shg.feature_member.domain.repository.MemberRepository

class GetMemberUseCase(private val memberRepository: MemberRepository) {
    suspend fun execute(memberId: Int) = memberRepository.getMember(memberId)
}