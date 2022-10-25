package com.arany.shg.feature_thrift.data.datasource

import com.arany.shg.data.db.ThriftDao
import com.arany.shg.feature_thrift.data.model.Thrift

class ThriftLocalDataSourceImpl(private val thriftDao: ThriftDao): ThriftLocalDataSource {

    override suspend fun getThrift(thriftId: Int): Thrift? {
        return thriftDao.getThrift(thriftId)
    }

    override suspend fun getThriftsOfCommittee(committeeId: Int): List<Thrift> {
        return thriftDao.getThriftsOfCommittee(committeeId)
    }

    override suspend fun getThriftsOfMember(memberId: Int): List<Thrift> {
        return thriftDao.getThriftsOfMember(memberId)
    }

    override suspend fun addThrift(thrift: Thrift) {
        thriftDao.addThrift(thrift)
    }

    override suspend fun updateThrift(thrift: Thrift) {
        thriftDao.updateThrift(thrift)
    }

    override suspend fun deleteThrift(thrift: Thrift) {
        thriftDao.deleteThrift(thrift)
    }
}