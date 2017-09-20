package com.example.rd.rdmvvm.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.rd.rdmvvm.R
import com.example.rd.rdmvvm.databinding.ActivityMainBinding
import com.example.rd.rdmvvm.entity.userDengLu
import com.example.rd.rdmvvm.entity.userInfo
import com.example.rd.rdmvvm.entity.userLogin
import com.example.rd.rdmvvm.model.retrofitHelper

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var user: userLogin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        user = userLogin()
        binding.user = user
    }

    public fun goLogin(view: View)
    {
        Toast.makeText(this,"aaaa",Toast.LENGTH_SHORT).show()
        var username: String ?=null
        var password: String ?=null
        username = binding.user!!.userName.get()
        password = binding.user!!.passWord.get()
        if (username!=null&&password!=null)
        {
            var user1: userDengLu = userDengLu (binding.user!!.userName.get(),binding.user!!.passWord.get())
            Toast.makeText(this,username+password,Toast.LENGTH_SHORT).show()

            retrofitHelper.instance.getUserLogin(binding,user1)
        }
        else
        {
            Toast.makeText(this,"用户名和密码不能为空",Toast.LENGTH_SHORT).show()
        }
    }
}
