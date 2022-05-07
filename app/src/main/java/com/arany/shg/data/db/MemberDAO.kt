package com.arany.shg.data.db

import androidx.room.*
import com.arany.shg.feature_member.data.model.Member
import kotlinx.coroutines.flow.Flow

@Dao
interface MemberDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(member: Member)

    @Query("SELECT * FROM Member WHERE shgId = :shgId")
    fun getMembersBySelfHelpGroupId(shgId: Int): Flow<List<Member>>

    @Query("SELECT * FROM Member WHERE memberId = :memberId")
    fun getMemberByMemberId(memberId: Int): Member

    @Delete
    suspend fun deleteMember(member: Member)
}