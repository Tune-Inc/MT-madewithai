package com.tuneinc.multitune.data.models

data class Track(
    val id: String,
    val title: String,
    val artist: String,
    val album: String,
    val duration: Long,
    val artworkUrl: String,
    val streamUrl: String
)