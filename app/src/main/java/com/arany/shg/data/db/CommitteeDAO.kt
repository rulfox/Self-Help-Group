package com.arany.shg.data.db

import androidx.room.*
import com.arany.shg.data.models.Committee
import com.arany.shg.feature_member.data.model.Member
import kotlinx.coroutines.flow.Flow

@Dao
interface CommitteeDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addCommittee(committee: Committee): Long

    @Query("SELECT * FROM Committee WHERE committeeId =:committeeId")
    fun getCommittee(committeeId: Int): Committee?

    @Query("SELECT * FROM Committee WHERE shgId = :shgId")
    fun getCommitteesBySelfHelpGroupId(shgId: Int): List<Committee>

    @Update
    fun updateCommittee(committee: Committee)

    @Delete
    suspend fun deleteMember(member: Member)
}