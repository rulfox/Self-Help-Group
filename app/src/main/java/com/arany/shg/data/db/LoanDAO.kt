package com.arany.shg.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.arany.shg.feature_attendance.data.model.Attendance
import com.arany.shg.feature_loan.data.models.Loan
import com.arany.shg.feature_loan.data.models.LoanWithDetails

@Dao
interface LoanDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLoan(loan: Loan): Long

    @Transaction
    @Query("SELECT * FROM Loan WHERE committeeId = :committeeId")
    suspend fun getLoansOfCommittee(committeeId: Int): List<LoanWithDetails>

    @Transaction
    @Query("SELECT * FROM Committee WHERE shgId= :shgId")
    suspend fun getLoans(shgId: Int): List<LoanWithDetails>

    @Transaction
    @Query("SELECT * FROM Loan WHERE loanId= :loanId")
    suspend fun getLoan(loanId: Int): LoanWithDetails?

    @Update
    suspend fun updateLoan(loan: Loan): Int

    @Delete
    suspend fun deleteLoan(loan: Loan): Int
}