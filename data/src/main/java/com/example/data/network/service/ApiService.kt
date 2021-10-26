package com.example.data.network.service

import com.example.data.entity.SampleEntity
import com.example.domain.domain.BalanceDomain
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("/api/shortlists/{shortlist_id}")
    fun getSample(
        @Path("shortlist_id") shortListID: String?,
    ): Observable<Response<SampleEntity>>


    @GET("/api/shortlists/{shortlist_id}")
    fun getBalance(
        @Path("userId") userId: String?,
    ): Observable<Response<BalanceDomain>>

}