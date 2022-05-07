package com.arany.shg.domain.usecase.attendance

import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_member.domain.repository.MemberRepository

class CreateMemberUseCase(private val memberRepository: MemberRepository) {
    suspend fun execute(member: Member) = memberRepository.createMember(member)
}