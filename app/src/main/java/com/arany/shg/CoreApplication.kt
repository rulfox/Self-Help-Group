package com.arany.shg

import android.app.Application
import android.util.Log
import com.arany.shg.core.util.Constants
import com.arany.shg.data.models.Committee
import com.arany.shg.data.models.Role
import com.arany.shg.data.util.DateUtils
import com.arany.shg.data.util.DateUtils.toString
import com.arany.shg.data.util.Resource
import com.arany.shg.feature_committee.domain.usecase.CommitteeUseCases
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_member.domain.use_case.MemberUseCases
import com.arany.shg.feature_member.presentation.util.add_member.AddMemberViewModel
import com.arany.shg.feature_role.domain.usecase.RoleUseCases
import com.arany.shg.feature_shg.data.model.SelfHelpGroup
import com.arany.shg.feature_shg.domain.use_case.SelfHelpGroupUseCases
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltAndroidApp
class CoreApplication: Application() {
    @Inject lateinit var roleUseCases: RoleUseCases

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            addRoles()
        }
    }

    private suspend fun addRoles() {
        roleUseCases.addRoleUseCase(Role(name = "Member", canWrite = false))
        roleUseCases.addRoleUseCase(Role(name = "Secretary", canWrite = true))
        roleUseCases.addRoleUseCase(Role(name = "President", canWrite = false))
        roleUseCases.addRoleUseCase(Role(name = "Treasurer", canWrite = false))
    }
}