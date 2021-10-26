package com.example.data.datasource

import com.example.data.entity.SampleEntity
import io.reactivex.Observable
import javax.inject.Inject


class CacheDataSource  @Inject constructor(): RemoteInterface {

    override fun getSample(sample: String?): Observable<SampleEntity?> {
        TODO("Not yet implemented")
    }

}