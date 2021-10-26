package com.example.data.repository


import com.example.domain.domain.SampleDomain
import com.example.domain.repository.UseCaseInterface
import com.example.data.datasource.DataSourceFactory
import com.example.data.mapper.SampleDataMapper
import com.example.domain.domain.BalanceDomain
import io.reactivex.Observable
import javax.inject.Inject


//it is an implementation for UseCase Interface
class UseCaseImplementation @Inject constructor(
    private val dataSourceFactory: DataSourceFactory,
    private val sampleDataMapper: SampleDataMapper
) : UseCaseInterface {

    override fun getSample(sample: String?): Observable<SampleDomain?> {
        return dataSourceFactory.retrieveRemoteDataSource().getSample(sample).map { entity ->
            return@map sampleDataMapper.mapEntityToDomain(entity)
        }
    }

    override fun getBalance(userId: String?): Observable<BalanceDomain?> {
        return dataSourceFactory.retrieveRemoteDataSource().getBalance(userId)
    }

}