package com.example.demoproject.network

import com.example.demoproject.posts.model.PostModel
import com.example.demoproject.utils.RequestRoutes
import io.reactivex.Observable
import retrofit2.http.*



interface RetroInterface {

    @GET(RequestRoutes.post)
    fun getPosts(): Observable<List<PostModel>>


}