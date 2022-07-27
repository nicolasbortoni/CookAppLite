package com.example.cookapplite.core.di

import com.example.cookapplite.LoginFeature.domain.User
import com.example.cookapplite.core.domain.SessionData
import com.example.cookapplite.core.framework.SessionManager
import com.example.cookapplite.core.framework.SessionManagerImpl
import com.example.cookapplite.core.usecases.GetSessionData
import com.example.cookapplite.core.usecases.SetSessionData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Singleton
    @Provides
    fun provideSessionData() : SessionData = SessionData(User("","","","","","", mutableListOf(),
        mutableListOf()))

    @Provides
    fun provideSessionManager(sessionData: SessionData) : SessionManager = SessionManagerImpl(sessionData)

    @Provides
    fun provideGetSessionData(sessionManager: SessionManager) : GetSessionData = GetSessionData(sessionManager)

    @Provides
    fun provideSetSessionData(sessionManager: SessionManager) : SetSessionData = SetSessionData(sessionManager)

}