package com.example.data.datasource

import javax.inject.Inject

class DataSourceFactory @Inject constructor(private val remoteDataSource: RemoteDataSource,
                                            private val cacheDataSource: CacheDataSource
) {
    fun retrieveRemoteDataSource(): RemoteDataSource {
        return remoteDataSource
    }

    fun retrieveCacheDataSource(): CacheDataSource {
        return cacheDataSource
    }

}