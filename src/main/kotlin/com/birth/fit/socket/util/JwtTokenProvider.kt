package com.birth.fit.socket.util

import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component


@Component
class JwtTokenProvider(
    @Value("\${auth.jwt.secret}")
    val secretKey: String
) {

    fun validationToken(token: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).body["type"] == "access_token"
        } catch (e: Exception) {
            false
        }
    }

    fun getEmail(token: String?): String? {
        return Jwts.parser().setSigningKey(secretKey)
            .parseClaimsJws(token).body.subject
    }
}