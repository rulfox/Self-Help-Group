package com.arany.shg.feature_thrift.domain.usecase

import com.arany.shg.feature_thrift.domain.repository.ThriftRepository
import javax.inject.Inject

class GetThriftUseCase @Inject constructor(private val thriftRepository: ThriftRepository) {
    suspend operator fun invoke(thriftId: Int) = thriftRepository.getThrift(thriftId)
}