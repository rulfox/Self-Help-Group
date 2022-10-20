package com.arany.shg

import android.app.Application
import com.arany.shg.feature_shg.data.model.SelfHelpGroup
import com.arany.shg.feature_shg.domain.use_case.SelfHelpGroupUseCases
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltAndroidApp
class CoreApplication: Application() {
    @Inject lateinit var selfHelpGroupUseCases: SelfHelpGroupUseCases
    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO + handleCoroutineException).launch{
            initializeSelfHelpGroup()
        }
    }

    private val handleCoroutineException = CoroutineExceptionHandler { coroutineContext, throwable ->

    }

    private suspend fun initializeSelfHelpGroup(){
        selfHelpGroupUseCases.createSelfHelpGroupUseCase(SelfHelpGroup(name = "Friends SHG", address = "Arany, North Aryad PO, Mannancherry, Alappuzha - 688538"))
    }
}