package com.example.data.network.convertor

import com.google.gson.annotations.SerializedName

data class Envelope<T>(@SerializedName("data") val data: T)


@Retention(AnnotationRetention.RUNTIME)
internal annotation class Enveloped