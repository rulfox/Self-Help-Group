package com.arany.shg.domain.usecase.attendance

import com.arany.shg.data.models.Member
import com.arany.shg.domain.repository.MemberRepository

class CreateMemberUseCase(private val memberRepository: MemberRepository) {
    suspend fun execute(member: Member) = memberRepository.createMember(member)
}