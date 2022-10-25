package com.arany.shg.feature_thrift.domain.usecase

import com.arany.shg.feature_thrift.domain.repository.ThriftRepository
import javax.inject.Inject

class GetThriftsOfMemberUseCase @Inject constructor(private val thriftRepository: ThriftRepository) {
    suspend operator fun invoke(memberId: Int) = thriftRepository.getThriftsOfMember(memberId)
}