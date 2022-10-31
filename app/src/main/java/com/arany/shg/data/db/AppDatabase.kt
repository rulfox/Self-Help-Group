package com.arany.shg.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arany.shg.data.models.*
import com.arany.shg.feature_attendance.data.model.Attendance
import com.arany.shg.feature_fine.data.models.Fine
import com.arany.shg.feature_fine.data.models.FinePayment
import com.arany.shg.feature_fine.data.models.FineType
import com.arany.shg.feature_loan.data.models.Loan
import com.arany.shg.feature_loan.data.models.LoanPayment
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_onboarding.data.data_source.LoginDao
import com.arany.shg.feature_shg.data.model.SelfHelpGroup
import com.arany.shg.feature_thrift.data.model.Thrift

@Database(
    entities = [SelfHelpGroup::class, Attendance::class, Committee::class, Fine::class, FinePayment::class, FineType::class, Loan::class, LoanPayment::class, Member::class, Role::class, Status::class, Thrift::class, Loan::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getSelfHelpGroupDAO(): SelfHelpGroupDAO
    abstract fun getMemberDAO(): MemberDAO
    abstract fun getLoginDao(): LoginDao
    abstract fun getRoleDao(): RoleDAO
    abstract fun getCommitteeDao(): CommitteeDAO
    abstract fun getAttendanceDao(): AttendanceDAO
    abstract fun getThriftDao(): ThriftDao
    abstract fun getLoanDao(): LoanDAO
}

