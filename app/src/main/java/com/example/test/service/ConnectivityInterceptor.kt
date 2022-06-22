package com.example.test.service

import android.content.Context
import com.example.test.model.User
import okhttp3.Interceptor
import kotlin.Throws
import com.example.test.service.NetworkUtil
import com.example.test.service.NoConnectivityException
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(private val mContext: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtil.isOnline(mContext)) {
            throw NoConnectivityException()
        }
        val builder = chain.request().newBuilder()
        builder.tag(User())
        return chain.proceed(builder.build())
    }
}