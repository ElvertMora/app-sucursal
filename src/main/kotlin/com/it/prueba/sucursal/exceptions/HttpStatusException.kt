package com.it.prueba.sucursal.exceptions

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpStatusCodeException

class HttpStatusException(statusCode: HttpStatus,
                          statusText: String,
                          responseBody: ByteArray? = ByteArray(0),
                          responseHeaders: HttpHeaders? = null) :
        HttpStatusCodeException(statusCode, statusText, responseHeaders, responseBody, null)
