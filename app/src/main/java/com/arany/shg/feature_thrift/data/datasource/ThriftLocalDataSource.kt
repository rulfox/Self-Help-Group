package com.arany.shg.feature_thrift.data.datasource

import com.arany.shg.feature_thrift.data.model.Thrift

interface ThriftLocalDataSource {
    suspend fun getThrift(thriftId: Int): Thrift?
    suspend fun getThriftsOfCommittee(committeeId: Int): List<Thrift>
    suspend fun getThriftsOfMember(memberId: Int): List<Thrift>
    suspend fun addThrift(thrift: Thrift)
    suspend fun updateThrift(thrift: Thrift)
    suspend fun deleteThrift(thrift: Thrift)
}