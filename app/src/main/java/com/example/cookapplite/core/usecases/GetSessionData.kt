package com.example.cookapplite.core.usecases

import com.example.cookapplite.core.framework.SessionManager

class GetSessionData(private val sessionManager: SessionManager) {
    operator fun invoke() = sessionManager.getSessionData()
}