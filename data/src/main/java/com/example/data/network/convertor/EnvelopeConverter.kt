package com.example.data.network.convertor

import okhttp3.ResponseBody
import retrofit2.Converter

class EnvelopeConverter<T> constructor(private val delegate: Converter<ResponseBody, Envelope<T>>) : Converter<ResponseBody, T> {

    override fun convert(value: ResponseBody): T {
        val envelope = delegate.convert(value)
        return envelope!!.data
    }
}