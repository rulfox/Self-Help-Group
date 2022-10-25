package com.arany.shg.feature_thrift.domain.repository

import com.arany.shg.feature_thrift.data.model.Thrift
import com.arany.shg.data.util.Resource

interface ThriftRepository {
    suspend fun getThrift(thriftId: Int): Resource<Thrift>
    suspend fun getThriftsOfCommittee(committeeId: Int): Resource<List<Thrift>>
    suspend fun getThriftsOfMember(memberId: Int): Resource<List<Thrift>>
    suspend fun addThrift(thrift: Thrift)
    suspend fun updateThrift(thrift: Thrift)
    suspend fun deleteThrift(thrift: Thrift)
}