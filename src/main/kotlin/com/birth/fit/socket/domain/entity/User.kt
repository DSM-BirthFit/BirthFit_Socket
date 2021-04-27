package com.birth.fit.socket.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
class User(

    @Id
    @Column(name = "email")
    internal val email: String,
)