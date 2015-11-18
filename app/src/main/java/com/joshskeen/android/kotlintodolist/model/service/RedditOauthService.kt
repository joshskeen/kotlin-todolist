package com.joshskeen.android.kotlintodolist.model.service

import android.content.Context
import com.joshskeen.android.kotlintodolist.model.service.payload.AuthResponse
import com.joshskeen.android.kotlintodolist.util.AuthUtil
import com.squareup.okhttp.*
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
import retrofit.http.Field
import retrofit.http.FormUrlEncoded
import retrofit.http.POST
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class RedditOauthService(context: Context) {

    val BASE_URL = "https://ssl.reddit.com/api/v1/"
    val CLIENT_CREDENTIALS = "https://oauth.reddit.com/grants/installed_client"
    val DEVICE_ID = UUID.randomUUID().toString()
    val serviceInterface: RedditOauthServiceInterface = getAPI()

    fun getAPI(): RedditOauthServiceInterface {
        val client = OkHttpClient()

        client.interceptors().add(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain?): Response? {
                val request = chain?.request()
                request?.newBuilder()?.addHeader("AUTHORIZATION", AuthUtil.encodeCredentialsForOauthAccessRequest())?.build()
                return chain?.proceed(request)
            }
        })

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(RedditOauthServiceInterface::class.java)
    }

    fun <T> Observable<T>.applySchedulers(): Observable<T> =
            this.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())


    interface RedditOauthServiceInterface {
        @FormUrlEncoded
        @POST("access_token")
        fun requestAccessToken(@Field("grant_type") grantType: String, @Field("device_id") deviceId: String): Observable<AuthResponse>
    }

    fun requestAccessToken(): Observable<AuthResponse> {
        return serviceInterface.requestAccessToken(CLIENT_CREDENTIALS, DEVICE_ID).applySchedulers()
    }

}