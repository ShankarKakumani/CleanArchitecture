package com.example.data.network.interceptor

import android.content.Context
import com.example.data.R
import com.example.domain.error.DomainError
import com.example.data.network.RestFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(@ApplicationContext val context: Context) :
    Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        if (!RestFactory.isOnline(context)) {
            throw DomainError.NoInternetException(context.getString(R.string.string_no_internet))
        }
        try {
            return chain.proceed(requestBuilder.build())
        }catch (ex: SocketTimeoutException){
            throw ex
        }
        catch (ex2: IOException){
            throw DomainError.NoInternetException(context.getString(R.string.string_no_internet))
        }
    }

}