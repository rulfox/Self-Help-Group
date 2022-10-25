package com.arany.shg.feature_thrift.domain.usecase

import com.arany.shg.feature_thrift.domain.repository.ThriftRepository
import javax.inject.Inject

class GetThriftsOfCommitteeUseCase @Inject constructor(private val thriftRepository: ThriftRepository) {
    suspend operator fun invoke(committeeId: Int) = thriftRepository.getThriftsOfCommittee(committeeId)
}