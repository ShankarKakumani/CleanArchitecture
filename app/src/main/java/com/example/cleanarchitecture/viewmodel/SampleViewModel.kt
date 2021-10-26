package com.example.cleanarchitecture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleanarchitecture.utils.Resource
import com.example.cleanarchitecture.utils.ResourceState
import com.example.domain.domain.BalanceDomain
import com.example.domain.domain.SampleDomain
import com.example.domain.usecase.BalanceUseCase
import com.example.domain.usecase.SampleUseCase
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class SampleViewModel  @Inject constructor(
    private val sampleUseCase: SampleUseCase,
    private val balanceUseCase: BalanceUseCase
): ViewModel() {

    var sampleLiveData: MutableLiveData<Resource<SampleDomain>> = MutableLiveData()


    fun getSampleData(userId: String?) {

        sampleLiveData.postValue(Resource(ResourceState.LOADING, null, null))

        sampleUseCase.execute(object : DisposableObserver<SampleDomain>() {
            override fun onNext(t: SampleDomain) {
                TODO("Not yet implemented")
            }

            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onComplete() {
                TODO("Not yet implemented")
            }

        }, SampleUseCase.Params(sample = userId))

    }

    fun getBalance(userId: String) {

        balanceUseCase.execute(object : DisposableObserver<BalanceDomain>() {
            override fun onNext(t: BalanceDomain) {

                //
                sampleLiveData.postValue(Resource(ResourceState.SUCCESS, null, null))
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }

        }, BalanceUseCase.Params(userId))
    }
}