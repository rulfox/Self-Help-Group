package com.arany.shg.feature_dashboard.data.models

enum class ServiceType(val serviceId: Int) {
    ADD_THRIFT(0),
    ADD_FINE(1),
    ADD_MEMBER(2),
    ADD_COMMITTEE(3),
    ADD_ATTENDANCE(4),
    ADD_ROLE(5),
    LIST_COMMITTEES(6),
    ADD_LOAN(7),
}