package com.example.domain.usecase

import com.example.domain.domain.BalanceDomain
import com.example.domain.executor.PostExecutionThread
import com.example.domain.executor.ThreadExecutor
import com.example.domain.repository.UseCaseInterface
import io.reactivex.Observable
import javax.inject.Inject

class BalanceUseCase @Inject constructor(

    private val useCaseInterface: UseCaseInterface,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread

) :ObservableUseCase<BalanceDomain?,BalanceUseCase.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<BalanceDomain?> {
        return useCaseInterface.getBalance(params.userId)
    }

    data class Params(
        val userId :String?
    )
}