package com.arany.shg.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arany.shg.data.models.*
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_onboarding.data.data_source.LoginDao
import com.arany.shg.feature_shg.data.model.SelfHelpGroup

@Database(
    entities = [SelfHelpGroup::class, Attendance::class, Committee::class, Fine::class, FinePayment::class, FineType::class, Loan::class, LoanPayment::class, Member::class, Role::class, Status::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getSelfHelpGroupDAO(): SelfHelpGroupDAO
    abstract fun getMemberDAO(): MemberDAO
    abstract fun getLoginDao(): LoginDao
    abstract fun getRoleDao(): RoleDAO
}

