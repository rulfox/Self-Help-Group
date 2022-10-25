package com.arany.shg.feature_thrift.domain.usecase

import javax.inject.Inject

data class ThriftUseCases @Inject constructor(
    var addThriftUseCase: AddThriftUseCase,
    var deleteThriftUseCase: DeleteThriftUseCase,
    var getThriftsOfCommitteeUseCase: GetThriftsOfCommitteeUseCase,
    var getThriftsOfMemberUseCase: GetThriftsOfMemberUseCase,
    var getThriftUseCase: GetThriftUseCase,
    var updateThriftUseCase: UpdateThriftUseCase,
)
