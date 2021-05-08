package com.birth.fit.socket.dto

import com.birth.fit.socket.domain.enums.PostType

class NotificationRequest(
    val postId: Int,
    val content: String,
    val postType: PostType
)