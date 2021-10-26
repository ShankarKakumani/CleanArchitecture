package com.example.domain.repository

import com.example.domain.domain.BalanceDomain
import com.example.domain.domain.SampleDomain
import io.reactivex.Observable

interface UseCaseInterface {
    fun getSample(sample: String?): Observable<SampleDomain?>
    fun getBalance(userId: String?) : Observable<BalanceDomain?>
}