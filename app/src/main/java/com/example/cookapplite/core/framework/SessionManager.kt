package com.example.cookapplite.core.framework

import com.example.cookapplite.LoginFeature.domain.User
import com.example.cookapplite.core.domain.SessionData

interface SessionManager {
    fun getSessionData() : SessionData
    fun setSessionData(user : User) : Boolean
}