package com.mukeshsolanki.countrypickerexample

import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val userId: String,
    val username: String,
    val token: String
)
