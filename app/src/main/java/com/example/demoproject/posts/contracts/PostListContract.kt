package com.example.demoproject.posts.contracts

import com.example.demoproject.posts.model.PostModel

class PostListContract {

    interface View{
        fun updateData(posts: List<PostModel>)
    }

    interface Presenter{
        fun loadPost(page: Int)
    }
}