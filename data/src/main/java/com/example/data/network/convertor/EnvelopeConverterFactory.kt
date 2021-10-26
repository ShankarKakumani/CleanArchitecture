package com.example.data.network.convertor

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class EnvelopeConverterFactory() : Converter.Factory() {
    override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {
        return if (!canHandle(annotations)) {
            null
        } else {
            val envelopeType = object: ParameterizedType {
                override fun getRawType(): Type {
                    return Envelope::class.java
                }

                override fun getOwnerType(): Type? {
                   return null
                }

                override fun getActualTypeArguments(): Array<Type> {
                    return arrayOf(type)
                }
            }
            val delegate: Converter<ResponseBody, Envelope<Type>> = retrofit.responseBodyConverter(envelopeType,annotations)
            EnvelopeConverter(delegate)
        }
    }


    private fun canHandle(annotations: Array<out Annotation>): Boolean {
        for (annotation in annotations) {
            if (annotation is Enveloped) {
                return true
            }
            break
        }
        return false
    }
}