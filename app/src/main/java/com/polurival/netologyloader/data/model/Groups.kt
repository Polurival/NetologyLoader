package com.polurival.netologyloader.data.model

data class Groups(
    val id: String,
    val link: String,
    val badge: Badge,
    val items: List<Items>,
    val title: String
)