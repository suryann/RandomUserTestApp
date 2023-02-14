package com.example.randomuser.data.model


/**
 * @author Soorianarayanan
 */
data class Login(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
    )
