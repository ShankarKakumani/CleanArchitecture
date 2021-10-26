package com.example.data.datasource

import com.example.data.entity.SampleEntity
import com.example.domain.domain.BalanceDomain

import io.reactivex.Observable

interface RemoteInterface {

    fun getSample(sample: String?): Observable<SampleEntity?>
    fun getBalance(userId: String?): Observable<BalanceDomain?>
}