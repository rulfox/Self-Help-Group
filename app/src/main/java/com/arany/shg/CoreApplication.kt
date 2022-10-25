package com.arany.shg

import android.app.Application
import android.util.Log
import com.arany.shg.core.util.Constants
import com.arany.shg.data.models.Role
import com.arany.shg.data.util.Resource
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
    @Inject lateinit var selfHelpGroupUseCases: SelfHelpGroupUseCases
    @Inject lateinit var memberUseCases: MemberUseCases
    @Inject lateinit var roleUseCases: RoleUseCases

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO + handleCoroutineException).launch {
            //addShg()
            //addRoles()
            //addMembers()
        }
        CoroutineScope(Dispatchers.IO + handleCoroutineException).launch {
            initializeListeners()
        }
    }

    private suspend fun initializeListeners() {
        selfHelpGroupUseCases.getAllSelfHelpGroupsUseCase.execute().collect{
            it.forEach { shg ->
                Log.e("SHG", shg.toString())
            }
        }
        memberUseCases.getMembersByShgIdUseCase(Constants.ShgId).collect{
            Log.e("Members", "Members fetching")
            Log.e("Members", "Size -> "+it.size)
            it.forEach { member ->
                Log.e("Members", member.toString())
            }
        }
        when(val fetchedRole = roleUseCases.getRolesUseCase()) {
            is Resource.Success -> {
                fetchedRole.data?.forEach { role ->
                    Log.e("Roles", role.toString())
                }
            }
            else -> {
                Log.e("Roles", "Roles are null")
            }
        }
    }

    private val handleCoroutineException = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("Exception", throwable.message.toString())
    }

    private suspend fun initializeSelfHelpGroup(){
        addShg()
        addRoles()
        addMembers()
    }

    private suspend fun addShg() {
        selfHelpGroupUseCases.createSelfHelpGroupUseCase(SelfHelpGroup(name = "Friends SHG", address = "Arany, North Aryad PO, Mannancherry, Alappuzha - 688538"))
    }

    private suspend fun addRoles() {
        roleUseCases.addRoleUseCase(Role(name = "Member"))
        roleUseCases.addRoleUseCase(Role(name = "Secretary"))
        roleUseCases.addRoleUseCase(Role(name = "President"))
        roleUseCases.addRoleUseCase(Role(name = "Treasurer"))
    }

    private suspend fun addMembers() {
        memberUseCases.createMemberUseCase(Member(shgId = Constants.ShgId, name = "Aswin", address = "Arany, North Aryad PO", phoneNumber = "7293318484", emailId = "awincodename47@gmail.com", roleId = 1, password = "One.rulfox000"))
        memberUseCases.createMemberUseCase(Member(shgId = Constants.ShgId, name = "Akhil", address = "Arany, North Aryad PO", phoneNumber = "7293318481", emailId = "aincodename47@gmail.com", roleId = 1, password = "One.rulfox000"))
        memberUseCases.createMemberUseCase(Member(shgId = Constants.ShgId, name = "Nikhil", address = "Arany, North Aryad PO", phoneNumber = "7293318482", emailId = "ancodename47@gmail.com", roleId = 1, password = "One.rulfox000"))
        memberUseCases.createMemberUseCase(Member(shgId = Constants.ShgId, name = "Nidhin", address = "Arany, North Aryad PO", phoneNumber = "7293318483", emailId = "acodename47@gmail.com", roleId = 1, password = "One.rulfox000"))
        memberUseCases.createMemberUseCase(Member(shgId = Constants.ShgId, name = "Abeesh", address = "Arany, North Aryad PO", phoneNumber = "7293318485", emailId = "aodename47@gmail.com", roleId = 1, password = "One.rulfox000"))
        memberUseCases.createMemberUseCase(Member(shgId = Constants.ShgId, name = "Vishnu", address = "Arany, North Aryad PO", phoneNumber = "7293318486", emailId = "adename47@gmail.com", roleId = 1, password = "One.rulfox000"))
    }
}