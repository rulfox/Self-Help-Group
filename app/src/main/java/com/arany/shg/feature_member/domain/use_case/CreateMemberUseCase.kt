package com.arany.shg.feature_member.domain.use_case

import com.arany.shg.feature_member.domain.repository.MemberRepository

class CreateMemberUseCase(private val memberRepository: MemberRepository) {
    suspend operator fun invoke(memberId: Int) = memberRepository.getMember(memberId)
}