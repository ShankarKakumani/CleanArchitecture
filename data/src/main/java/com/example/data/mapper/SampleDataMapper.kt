package com.example.data.mapper


import com.example.data.entity.SampleEntity
import com.example.domain.domain.SampleDomain
import javax.inject.Inject

class SampleDataMapper @Inject constructor() {
    fun mapEntityToDomain(sampleEntity: SampleEntity): SampleDomain {
        return SampleDomain(
            userName = sampleEntity.userName
        )
    }

    fun mapDomainToEntity(sampleDomain: SampleDomain): SampleEntity {

       return SampleEntity(
           userName = sampleDomain.userName
       )
    }
}