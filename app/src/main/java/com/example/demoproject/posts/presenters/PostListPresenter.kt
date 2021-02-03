package com.example.demoproject.posts.presenters

import com.example.demoproject.network.APICallback
import com.example.demoproject.network.getPosts
import com.example.demoproject.posts.contracts.PostListContract
import com.example.demoproject.posts.model.PostModel
import java.lang.ref.WeakReference

@Suppress("UNCHECKED_CAST")
class PostListPresenter(private val v : WeakReference<PostListContract.View>): PostListContract.Presenter, APICallback{

    override fun loadPost(page: Int) {
        getPosts(this)
    }

    override fun success(data: Any?) {
        v.get()?.updateData(data as List<PostModel>)
    }

    override fun failed(message: String?) {
        // Show an alert or something
    }

    override fun error(message: String?) {
        // Show an alert or something with retry
    }

}