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

    @Bean
    fun socketIOConfig(): com.corundumstudio.socketio.Configuration {
        val config = com.corundumstudio.socketio.Configuration()
        config.port = port

        return config
    }

    @Bean
    fun socketIOServer(): SocketIOServer {
        val server = SocketIOServer(socketIOConfig())
        server.start()

        return server
    }

    @PreDestroy
    fun stop() {
        socketIOServer().stop()
    }
}