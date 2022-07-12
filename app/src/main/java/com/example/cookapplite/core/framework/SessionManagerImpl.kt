package com.example.cookapplite.core.framework

import com.example.cookapplite.core.domain.SessionData
import javax.inject.Inject

class SessionManagerImpl @Inject constructor(
    private val sessionData: SessionData
) : SessionManager {
    override fun getSessionData(): SessionData {
        return sessionData
    }

    override fun setSessionData(newSessionData: SessionData): Boolean {
        sessionData.userLoged = newSessionData.userLoged
        return true
    }

}