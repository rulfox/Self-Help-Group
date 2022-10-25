package com.arany.shg.feature_thrift.data.repository

import com.arany.shg.data.util.Resource
import com.arany.shg.feature_thrift.data.model.Thrift
import com.arany.shg.feature_thrift.data.datasource.ThriftLocalDataSource
import com.arany.shg.feature_thrift.domain.repository.ThriftRepository
import javax.inject.Inject

class ThriftRepositoryImpl @Inject constructor(private var thriftLocalDataSource: ThriftLocalDataSource): ThriftRepository {
    override suspend fun getThrift(thriftId: Int): Resource<Thrift> {
        return thriftLocalDataSource.getThrift(thriftId)?.let {
            Resource.Success(it)
        } ?: Resource.Error("Thrift not found with thriftId $thriftId")
    }

    override suspend fun getThriftsOfCommittee(committeeId: Int): Resource<List<Thrift>> {
        val thrifts = thriftLocalDataSource.getThriftsOfCommittee(committeeId)
        return if(thrifts.isEmpty())
            Resource.Error("Thrift not found for committeeId $committeeId")
        else Resource.Success(thrifts)
    }

    override suspend fun getThriftsOfMember(memberId: Int): Resource<List<Thrift>> {
        val thrifts = thriftLocalDataSource.getThriftsOfMember(memberId)
        return if(thrifts.isEmpty())
            Resource.Error("Thrift not found for memberId $memberId")
        else Resource.Success(thrifts)
    }

    override suspend fun addThrift(thrift: Thrift) {
        thriftLocalDataSource.addThrift(thrift)
    }

    override suspend fun updateThrift(thrift: Thrift) {
        thriftLocalDataSource.updateThrift(thrift)
    }

    override suspend fun deleteThrift(thrift: Thrift) {
        thriftLocalDataSource.updateThrift(thrift)
    }
}