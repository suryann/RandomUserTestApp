package com.example.randomuser.data.exceptions


/**
 * @author Soorianarayanan
 */
class NetworkException : Exception(message){
    companion object {
        const val message = "An error occurred while retrieving data from the server"
    }
}