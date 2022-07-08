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
import com.example.cookapplite.LoginFeature.usecases.LoginUser
import com.example.cookapplite.RecipeFeature.data.RecipesRepository
import com.example.cookapplite.RecipeFeature.framework.RecipeDataSource
import com.example.cookapplite.RecipeFeature.framework.RecipeDataSourceImpl
import com.example.cookapplite.RecipeFeature.framework.RecipeStorage
import com.example.cookapplite.RecipeFeature.framework.RecipeStorageImpl
import com.example.cookapplite.RecipeFeature.ui.viewmodel.AddRecipeViewModel
import com.example.cookapplite.RecipeFeature.ui.viewmodel.RecipeListViewModel
import com.example.cookapplite.RecipeFeature.usecases.CreateRecipe
import com.example.cookapplite.RecipeFeature.usecases.GetRecipesFromRepository
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
    fun provideUsersRepository(userAuthentication: UserAuthentication, userDataSource: UserDataSource, userStorage: UserStorage) : UsersRepository = UsersRepository(userAuthentication, userDataSource, userStorage)

    @Provides
    fun provideCreateUser(usersRepository: UsersRepository) : CreateUser = CreateUser(usersRepository)

    @Provides
    fun provideLoginUser(usersRepository: UsersRepository) : LoginUser = LoginUser(usersRepository)

    @Provides
    fun provideLoginViewModel(loginUser: LoginUser) : LoginViewModel = LoginViewModel(loginUser)

    @Provides
    fun provideAddUserViewModel(createUser: CreateUser) : AddUserViewModel = AddUserViewModel(createUser)

}