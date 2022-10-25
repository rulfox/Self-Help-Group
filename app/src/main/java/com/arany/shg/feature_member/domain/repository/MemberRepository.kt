package com.arany.shg.feature_member.domain.repository

import com.arany.shg.data.util.Resource
import com.arany.shg.feature_member.data.model.Member
import kotlinx.coroutines.flow.Flow

interface MemberRepository{
      suspend fun createMember(member: Member): Long
      suspend fun getMember(memberId: Int): Resource<Member>
      suspend fun getMembersOfSelfHelpGroup(shgId: Int): Flow<List<Member>>
}