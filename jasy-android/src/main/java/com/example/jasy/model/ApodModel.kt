package com.example.jasy.model

import java.io.Serializable

data class ApodModel(
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
): Serializable