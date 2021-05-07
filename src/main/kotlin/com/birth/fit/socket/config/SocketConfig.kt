package com.birth.fit.socket.config

import com.corundumstudio.socketio.SocketIOServer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.annotation.PreDestroy

@Configuration
class SocketConfig(
    @Value("\${server.socket.port}")
    private val port: Int
) {

    private lateinit var server: SocketIOServer

    @Bean
    fun SocketIOServer(): SocketIOServer {
        val config = com.corundumstudio.socketio.Configuration()
        config.port = port
        config.origin = "http://13.124.184.19:3000"

        val server = SocketIOServer(config)

        server.start()

        this.server = server

        return server
    }

    @PreDestroy
    fun stop() {
        server.stop()
    }
}