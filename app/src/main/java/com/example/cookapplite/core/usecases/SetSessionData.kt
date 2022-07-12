package com.example.cookapplite.core.usecases

import com.example.cookapplite.core.domain.SessionData
import com.example.cookapplite.core.framework.SessionManager

class SetSessionData(private val sessionManager: SessionManager) {
    fun invoke(sessionData: SessionData) = sessionManager.setSessionData(sessionData)
}