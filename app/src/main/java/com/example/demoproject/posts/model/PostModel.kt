package com.example.demoproject.posts.model

//data class PostResponse(
//    data
//)

data class PostModel(
    val userId: String,
    val id: String,
    val title: String,
    val body: String,
    var selected: Boolean = false
)
