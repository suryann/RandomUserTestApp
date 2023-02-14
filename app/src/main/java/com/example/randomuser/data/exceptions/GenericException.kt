package com.example.randomuser.data.exceptions


/**
 * @author Soorianarayanan
 */
class GenericException: Exception(message) {
    companion object {
        const val message = "An unexpected error has occurred, please contact customer support"
    }
}