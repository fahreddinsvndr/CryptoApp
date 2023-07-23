package com.fahreddinsevindir.cryptoapp.data.intercepter

import com.fahreddinsevindir.cryptoapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(request().newBuilder().addHeader("X-CMC_PRO_API_KEY", BuildConfig.API_KEY).build())
    }
}