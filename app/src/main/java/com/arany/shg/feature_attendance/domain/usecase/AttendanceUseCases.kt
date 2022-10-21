package com.arany.shg.feature_attendance.domain.usecase

data class AttendanceUseCases(
    val createAttendanceUseCase: CreateAttendanceUseCase,
    val createAttendancesUseCase: CreateAttendancesUseCase,
    val updateAttendanceUseCase: UpdateAttendanceUseCase,
    val deleteAttendanceUseCase: DeleteAttendanceUseCase,
    val getAttendanceUseCase: GetAttendanceUseCase,
    val getAttendancesUseCase: GetAttendancesUseCase,
)
