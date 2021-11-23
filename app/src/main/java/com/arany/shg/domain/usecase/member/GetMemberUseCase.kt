package com.arany.shg.domain.usecase.member

import com.arany.shg.domain.repository.MemberRepository

class GetMemberUseCase(private val memberRepository: MemberRepository) {
    suspend fun execute(memberId: Int) = memberRepository.getMember(memberId)
}