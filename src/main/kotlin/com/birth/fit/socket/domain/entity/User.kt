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


    @Column(name = "user_id")
    internal var userId: String,

    @Column(name = "password")
    internal var password: String,

    @Column(name = "image")
    internal var image: String? = null
)