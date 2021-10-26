package com.example.data.datasource

import com.example.data.entity.SampleEntity
import com.example.data.network.remote.user.ServiceInterface
import com.example.domain.domain.BalanceDomain
import io.reactivex.Observable
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val serviceInterface: ServiceInterface,
): RemoteInterface {

    override fun getSample(sample: String?): Observable<SampleEntity?> {
        return serviceInterface.getSample(sample)
    }

    override fun getBalance(userId: String?): Observable<BalanceDomain?> {
        return serviceInterface.getBalance(userId)
    }

}