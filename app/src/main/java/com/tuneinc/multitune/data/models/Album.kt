package com.tuneinc.multitune.data.models

data class Album(
    val id: String,
    val title: String,
    val artist: String,
    val artworkUrl: String,
    val trackCount: Int
)