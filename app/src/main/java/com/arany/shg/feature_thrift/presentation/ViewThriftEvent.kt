package com.arany.shg.feature_thrift.presentation

import com.arany.shg.data.models.Committee
import com.arany.shg.feature_member.data.model.Member

sealed class ViewThriftEvent{
    object RefreshThrifts: ViewThriftEvent()
    object HideThriftErrorAlertDialog: ViewThriftEvent()
    object ShowThriftErrorAlertDialog: ViewThriftEvent()
}
