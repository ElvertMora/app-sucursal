package com.it.prueba.sucursal

import okhttp3.mockwebserver.MockWebServer
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile


@TestConfiguration
@Profile("test-integration")
class MockWebServerConfiguration {
    @Bean
    fun mockWebServer(): MockWebServer {
        val mockWebServer = MockWebServer()
        mockWebServer.start()
        return mockWebServer
    }
}
