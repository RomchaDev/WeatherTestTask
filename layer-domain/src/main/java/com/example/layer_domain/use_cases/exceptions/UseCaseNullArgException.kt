package com.example.layer_domain.use_cases.exceptions

import java.io.IOException

class UseCaseNullArgException(message: String? = null) :
    IOException(message ?: DEFAULT_MESSAGE) {
        
        companion object {
            private const val DEFAULT_MESSAGE = "UseCase's argument can not be null"
        }
}