package com.birth.fit.socket.config

import com.corundumstudio.socketio.SocketIOServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import javax.annotation.PreDestroy

@Configuration
class SocketConfig(
    @Value("\${server.socket.port}")
    private val port: Int,
    @Value("\${server.socket.hostname}")
    private val hostname: String,
    @Autowired @Lazy private var server: SocketIOServer
) {

    @Bean
    fun SocketIOServer(): SocketIOServer {
        val config = com.corundumstudio.socketio.Configuration()
        config.hostname = hostname
        config.port = port
        config.origin = "*:*"

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