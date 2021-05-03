package com.birth.fit.socket.domain.repository

import com.birth.fit.socket.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<User, String> {
    fun findByEmail(email: String?): Optional<User?>
}