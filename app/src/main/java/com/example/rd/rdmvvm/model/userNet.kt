package com.example.rd.rdmvvm.model

import com.example.rd.rdmvvm.entity.HttpResult
import com.example.rd.rdmvvm.entity.userInfo
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
/**
 * Created by rd on 2017/9/19.
 */

interface userNet
{
    companion object {
        val BASE_URL: String = "http://10.0.2.2:8080/jike1504/user/"
    }

    @POST("login")
    fun  getLoginToken(@Body user: RequestBody): Observable<HttpResult<userInfo>>
}