package com.example.data.network.remote.user


import com.example.data.entity.SampleEntity
import com.example.domain.domain.BalanceDomain
import io.reactivex.Observable

interface ServiceInterface {
    fun getSample(username: String?): Observable<SampleEntity?>
    fun getBalance(userId: String?): Observable<BalanceDomain?>
}