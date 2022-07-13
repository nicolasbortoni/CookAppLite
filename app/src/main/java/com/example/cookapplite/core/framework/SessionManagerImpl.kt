package com.example.cookapplite.core.framework

import com.example.cookapplite.LoginFeature.domain.User
import com.example.cookapplite.core.domain.SessionData

import javax.inject.Inject

class SessionManagerImpl @Inject constructor(
    private val sessionData: SessionData
) : SessionManager {
    override fun getSessionData(): User {
        return sessionData.userLoged
    }

    override fun setSessionData(user: User): Boolean {
        sessionData.userLoged = user
        return true
    }

}