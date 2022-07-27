package com.example.cookapplite.LoginFeature.di

import com.example.cookapplite.LoginFeature.data.UsersRepository
import com.example.cookapplite.LoginFeature.framework.UserAuthenticationImpl
import com.example.cookapplite.LoginFeature.framework.UserDataSourceImpl
import com.example.cookapplite.LoginFeature.data.UserAuthentication
import com.example.cookapplite.LoginFeature.data.UserDataSource
import com.example.cookapplite.LoginFeature.data.UserStorage
import com.example.cookapplite.LoginFeature.framework.UserStorageImpl
import com.example.cookapplite.LoginFeature.ui.viewmodel.AddUserViewModel
import com.example.cookapplite.LoginFeature.ui.viewmodel.LoginViewModel
import com.example.cookapplite.LoginFeature.usecases.CreateUser
import com.example.cookapplite.LoginFeature.usecases.GetUserLoged
import com.example.cookapplite.LoginFeature.usecases.LoginUser
import com.example.cookapplite.core.usecases.GetSessionData
import com.example.cookapplite.core.usecases.SetSessionData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoginDi {

    @Provides
    fun provideUserAuthentication() : UserAuthentication = UserAuthenticationImpl()

    @Provides
    fun provideUserDataSource() : UserDataSource = UserDataSourceImpl()

    @Provides
    fun provideUserStorage() : UserStorage = UserStorageImpl()

    @Provides
    fun provideUsersRepository(userAuthentication: UserAuthentication, userDataSource: UserDataSource, userStorage: UserStorage, getSessionData: GetSessionData) : UsersRepository = UsersRepository(userAuthentication, userDataSource, userStorage,getSessionData)

    @Provides
    fun provideCreateUser(usersRepository: UsersRepository) : CreateUser = CreateUser(usersRepository)

    @Provides
    fun provideLoginUser(usersRepository: UsersRepository) : LoginUser = LoginUser(usersRepository)

    @Provides
    fun provideGetUserLoged(usersRepository: UsersRepository) : GetUserLoged = GetUserLoged(usersRepository)

    @Provides
    fun provideLoginViewModel(loginUser: LoginUser, setSessionData: SetSessionData ,getUserLoged: GetUserLoged) : LoginViewModel = LoginViewModel(loginUser,setSessionData,getUserLoged)

    @Provides
    fun provideAddUserViewModel(createUser: CreateUser, setSessionData: SetSessionData, getUserLoged: GetUserLoged) : AddUserViewModel = AddUserViewModel(createUser,setSessionData,getUserLoged)

}