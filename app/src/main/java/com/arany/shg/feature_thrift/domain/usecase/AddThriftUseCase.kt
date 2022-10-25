package com.arany.shg.feature_thrift.domain.usecase

import com.arany.shg.feature_thrift.data.model.Thrift
import com.arany.shg.feature_thrift.domain.repository.ThriftRepository
import javax.inject.Inject

class AddThriftUseCase @Inject constructor(private val thriftRepository: ThriftRepository) {
    suspend operator fun invoke(thrift: Thrift) = thriftRepository.addThrift(thrift)
}