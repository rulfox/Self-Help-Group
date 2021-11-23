package com.arany.shg.domain.repository

import com.arany.shg.data.models.Member
import com.arany.shg.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface MemberRepository{
      suspend fun createMember(member: Member)
      suspend fun getMember(memberId: Int): Resource<Member>
      suspend fun getMembersOfSelfHelpGroup(shgId: Int): Flow<List<Member>>
}