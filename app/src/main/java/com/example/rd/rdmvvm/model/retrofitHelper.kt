package com.example.rd.rdmvvm.model


import android.util.Log
import android.widget.Toast
import com.example.rd.rdmvvm.databinding.ActivityMainBinding
import com.example.rd.rdmvvm.entity.HttpResult
import com.example.rd.rdmvvm.entity.userDengLu
import com.example.rd.rdmvvm.entity.userInfo
import com.example.rd.rdmvvm.entity.userLogin
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.observers.DefaultObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import org.reactivestreams.Subscriber
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by rd on 2017/9/19.
 */

class retrofitHelper {
    companion object jingtai {
        private val DEFAULT_TIMEOUT: Long = 10
        public val instance: retrofitHelper = retrofitHelper()
    }

    private var retrofit: Retrofit
    private var usernet: userNet
    private var builder: OkHttpClient.Builder

    constructor() {
        builder = OkHttpClient.Builder()
        builder.connectTimeout(jingtai.DEFAULT_TIMEOUT, TimeUnit.SECONDS)

        retrofit = Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(userNet.BASE_URL)
                .build()

        usernet = retrofit.create(userNet::class.java)
    }

    fun getUserLogin(binding: ActivityMainBinding,user: userDengLu) {
        var gson: Gson = Gson()
        var userJson: String = gson.toJson(user)
        var body: RequestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), userJson)
        Log.d("JSON",userJson.toString())
        usernet.getLoginToken(body)
                .map{
                    t ->
                    if (t.code ==1)
                    {
                        return@map t.data
                    }
                    else
                    {
                        throw Exception(t.msg)
                    }
                   }
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    t: userInfo?->
                    binding.user?.sessionId?.set(t?.sessionId)
                })

    }
}