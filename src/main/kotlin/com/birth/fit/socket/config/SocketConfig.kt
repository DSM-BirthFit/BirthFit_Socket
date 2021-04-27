package com.birth.fit.socket.config

import com.corundumstudio.socketio.SocketIOServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.annotation.PreDestroy

@Configuration
class SocketConfig(
    @Value("\${server.socket.port:8000}")
    private val port: Int
) {

    private lateinit var server: SocketIOServer

    @Bean
    fun SocketIOServer(): SocketIOServer {
        val configuration = com.corundumstudio.socketio.Configuration()
        configuration.port = port

        val server = SocketIOServer(configuration)

        server.start()

        this.server = server

        return server
    }

    @PreDestroy
    fun stop() {
        server.stop()
    }
}