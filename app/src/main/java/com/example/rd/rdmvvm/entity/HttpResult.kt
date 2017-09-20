package com.example.rd.rdmvvm.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by rd on 2017/9/19.
 */

data class HttpResult<T>(@SerializedName("code") var code: Int,@SerializedName("msg") var msg: String,@SerializedName("data") var data: T?)