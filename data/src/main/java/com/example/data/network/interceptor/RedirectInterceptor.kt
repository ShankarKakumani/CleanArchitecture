package com.example.data.network.interceptor

import android.content.Context
import com.example.domain.error.DomainError
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RedirectInterceptor @Inject constructor(@ApplicationContext val context: Context) :
    Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val priorResponse = response.priorResponse
        if (priorResponse != null) {
            if (priorResponse.isRedirect) {
                throw DomainError.NoInternetException("No internet connection")
            }
        }
        return response
    }

}