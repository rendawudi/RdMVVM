package com.example.rd.rdmvvm.entity

import android.database.Observable
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableInt


/**
 * Created by rd on 2017/9/17.
 */

data class userLogin(var sessionId: ObservableField<String> = ObservableField(""),var userName:ObservableField<String> = ObservableField(""), var passWord: ObservableField<String> = ObservableField(""))