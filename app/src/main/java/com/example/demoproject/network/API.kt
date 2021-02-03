package com.example.demoproject.network

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

// No need for more abstraction cause then it will be too much
// and unnecessary as this project won't have that much of variations
interface APICallback
{
    fun success(data: Any? = "")
    fun failed(message: String?)
    fun error(message: String?)
}

@SuppressLint("CheckResult")
fun getPosts(callback: APICallback){
    ServiceGenerator
        .getClient()
        .getPosts()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({

            // API should return success or failure
            // by which we can decide to store in local or not
            // for now not storing anything locally...
            callback.success(it)

        }, {
            it.printStackTrace()
            callback.error("Error: " + it.message)
        })
}