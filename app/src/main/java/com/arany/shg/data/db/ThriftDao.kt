package com.arany.shg.data.db

import androidx.room.*
import com.arany.shg.feature_thrift.data.model.Thrift

@Dao
interface ThriftDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addThrift(thrift: Thrift)

    @Query("SELECT * FROM Thrift WHERE thriftId = :thriftId")
    suspend fun getThrift(thriftId: Int): Thrift?

    @Query("SELECT * FROM Thrift WHERE committeeId = :committeeId")
    suspend fun getThriftsOfCommittee(committeeId: Int): List<Thrift>

    @Query("SELECT * FROM Thrift WHERE memberId = :memberId")
    suspend fun getThriftsOfMember(memberId: Int): List<Thrift>

    @Update
    suspend fun updateThrift(thrift: Thrift)

    @Delete
    suspend fun deleteThrift(thrift: Thrift)
}