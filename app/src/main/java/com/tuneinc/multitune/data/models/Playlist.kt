package com.tuneinc.multitune.data.models

data class Playlist(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val trackCount: Int
)