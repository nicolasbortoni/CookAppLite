package com.example.cookapplite.core.framework

import com.example.cookapplite.core.domain.SessionData

interface SessionManager {
    fun getSessionData() : SessionData
    fun setSessionData(sessionData: SessionData) : Boolean
}