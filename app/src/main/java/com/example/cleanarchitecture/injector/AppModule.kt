package com.example.cleanarchitecture.injector


import com.example.data.BuildConfig
import com.example.cleanarchitecture.utils.UIThread
import com.example.data.Constants
import com.example.data.JobExecutor
import com.example.data.network.RestFactory
import com.example.data.network.interceptor.BasicAuthInterceptor
import com.example.data.network.interceptor.NetworkInterceptor
import com.example.data.network.interceptor.RedirectInterceptor
import com.example.data.network.remote.user.ServiceInterface
import com.example.data.network.remote.user.ServiceImplementation
import com.example.data.network.service.ApiService
import com.example.data.repository.UseCaseImplementation
import com.example.domain.executor.PostExecutionThread
import com.example.domain.executor.ThreadExecutor
import com.example.domain.repository.UseCaseInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule  {

    @Provides
    fun provideBaseUrl() = Constants.hostUrl

    @JvmStatic
    @Singleton
    @Provides
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @JvmStatic
    @Singleton
    @Provides
    fun providePostThreadExecutor(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Singleton
    @Provides
    fun provideOkHttp(networkInterceptor: NetworkInterceptor,
                      redirectInterceptor: RedirectInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(redirectInterceptor)
            .addInterceptor(networkInterceptor)
            .addInterceptor(BasicAuthInterceptor(Constants.clientId,Constants.httpClientSecretHash))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit {
        return RestFactory.makeRetrofit(isDebug = BuildConfig.DEBUG, okHttpClient = okHttpClient)
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @JvmStatic
    @Singleton
    @Provides
    fun provideUserRemote(serviceImpl: ServiceImplementation): ServiceInterface {
        return serviceImpl
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideUserRepository(userDataImplementation: UseCaseImplementation): UseCaseInterface {
        return userDataImplementation
    }




}