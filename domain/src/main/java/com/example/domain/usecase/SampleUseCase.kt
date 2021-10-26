package com.example.domain.usecase

import com.example.domain.executor.PostExecutionThread
import com.example.domain.executor.ThreadExecutor
import com.example.domain.domain.SampleDomain
import com.example.domain.repository.UseCaseInterface
import io.reactivex.Observable
import javax.inject.Inject

class SampleUseCase @Inject constructor(

    private val useCaseInterface: UseCaseInterface,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread

) :ObservableUseCase<SampleDomain?,SampleUseCase.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<SampleDomain?> {
        return useCaseInterface.getSample(sample = params.sample)
    }

    data class Params(
        val sample :String?
    )
}