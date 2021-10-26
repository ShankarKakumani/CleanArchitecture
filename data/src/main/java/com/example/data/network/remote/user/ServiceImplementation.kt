package com.example.data.network.remote.user

import android.preference.PreferenceManager
import com.example.data.entity.SampleEntity
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken

import com.example.data.network.service.ApiService
import com.example.domain.domain.BalanceDomain
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

fun <T> Response<T>.getMessageFromErrorBody(): String? {
    val gson = Gson()
    val type = object : TypeToken<Any>() {}.type
    var errorResponse: String? = null
    try {
        val errorResponseObj: Any = gson.fromJson(this.errorBody()!!.charStream(), type)
        errorResponse = (errorResponseObj as LinkedTreeMap<*, *>)["message"] as String?
    } catch (e: Exception) {
    }

    return errorResponse
}

class ServiceImplementation  @Inject constructor(
    private val apiService: ApiService,
    private val preferenceManager: PreferenceManager
) : ServiceInterface {

    override fun getSample(username: String?): Observable<SampleEntity?> {
        return apiService.getSample(username).map {
            if (it.isSuccessful) {
                return@map it.body()
            } else {
                val errorMsg = it.getMessageFromErrorBody()
                throw Exception(errorMsg ?: "Something went wrong: error ${it.code()}")
            }
        }.map {
            return@map it
        }
    }

    override fun getBalance(userId: String?): Observable<BalanceDomain?> {
        return apiService.getBalance(userId).map {
            if (it.isSuccessful) {
                return@map it.body()
            } else {
                val errorMsg = it.getMessageFromErrorBody()
                throw Exception(errorMsg ?: "Something went wrong: error ${it.code()}")
            }
        }.map {
            return@map it
        }
    }


}
