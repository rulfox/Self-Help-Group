package com.arany.shg.data.db

import androidx.room.*
import com.arany.shg.feature_fine.data.models.Fine
import com.arany.shg.feature_fine.data.models.FineWithDetails

@Dao
interface FineDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFine(Fine: Fine): Long

    @Transaction
    @Query("SELECT * FROM Fine WHERE committeeId = :committeeId")
    suspend fun getFinesFromCommittee(committeeId: Int): List<Fine>

    @Transaction
    @Query("SELECT * FROM Fine WHERE committeeId = :committeeId")
    suspend fun getFinesFromCommitteeWithDetails(committeeId: Int): List<FineWithDetails>

    @Transaction
    @Query("SELECT * FROM Committee WHERE shgId= :shgId")
    suspend fun getFines(shgId: Int): List<FineWithDetails>

    @Transaction
    @Query("SELECT * FROM Committee WHERE shgId= :shgId")
    suspend fun getFinesWithDetails(shgId: Int): List<FineWithDetails>

    @Transaction
    @Query("SELECT * FROM Fine WHERE FineId= :FineId")
    suspend fun getFineWithDetails(FineId: Int): FineWithDetails?

    @Transaction
    @Query("SELECT * FROM Fine WHERE FineId= :FineId")
    suspend fun getFine(FineId: Int): Fine?

    @Update
    suspend fun updateFine(Fine: Fine): Int

    @Delete
    suspend fun deleteFine(Fine: Fine): Int
}