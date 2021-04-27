package com.birth.fit.socket.controller

import com.birth.fit.socket.service.SocketService
import com.corundumstudio.socketio.SocketIOServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class SocketController(
    @Autowired private val server: SocketIOServer,
    @Autowired private val service: SocketService
) {

    @PostConstruct
    fun socket() {
    }
}