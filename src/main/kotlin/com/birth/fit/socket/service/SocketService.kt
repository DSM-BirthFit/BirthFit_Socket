package com.birth.fit.socket.service

import com.birth.fit.socket.domain.entity.User
import com.birth.fit.socket.domain.repository.UserRepository
import com.birth.fit.socket.util.JwtTokenProvider
import com.corundumstudio.socketio.SocketIOClient
import com.corundumstudio.socketio.SocketIOServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SocketService(
    @Autowired private val server: SocketIOServer,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val jwtTokenProvider: JwtTokenProvider
) {

    fun connect(client: SocketIOClient) {
        val token = client.handshakeData.getSingleUrlParam("token")
        if(!jwtTokenProvider.validationToken(token)) {
            println("토큰이 유효하지 않습니다.")
            client.disconnect()
        }

        val user: User?
        try {
            user = userRepository.findByEmail(jwtTokenProvider.getEmail(token))
                .orElseThrow { RuntimeException() }
            client["user"] = user
            println("ConnectedId : ${client.sessionId} UserId : ${user?.userId}")
        } catch (e: Exception) {
            println("유저를 찾을 수 없습니다.")
            client.disconnect()
        }
    }

    fun disConnect(client: SocketIOClient) {
        println("DisconnectedId: ${client.sessionId}")
    }

    fun join(client: SocketIOClient, userEmail: String) {
        val user: User? = client["user"]
        if(user == null) {
            println("유저 정보를 찾을 수 없습니다.")
            client.disconnect()
        }

        client.joinRoom(userEmail)
        println("join userEmail: $userEmail")
    }

    fun leave(client: SocketIOClient, userEmail: String) {
        val user: User? = client["user"]
        if(user == null) {
            println("유저 정보를 찾을 수 없습니다.")
            client.disconnect()
        }

        client.leaveRoom(userEmail)
        println("leave userEmail: $userEmail")
    }
}