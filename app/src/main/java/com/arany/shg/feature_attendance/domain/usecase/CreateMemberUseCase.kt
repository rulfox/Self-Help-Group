package com.arany.shg.feature_attendance.domain.usecase

import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_member.domain.repository.MemberRepository

class CreateMemberUseCase(private val memberRepository: MemberRepository) {
    suspend operator fun invoke(member: Member) = memberRepository.createMember(member)
}