package com.example.cookapplite.core.usecases

import com.example.cookapplite.LoginFeature.domain.User
import com.example.cookapplite.core.domain.SessionData
import com.example.cookapplite.core.framework.SessionManager

class SetSessionData(private val sessionManager: SessionManager) {
    operator fun invoke(user: User) = sessionManager.setSessionData(user)
}