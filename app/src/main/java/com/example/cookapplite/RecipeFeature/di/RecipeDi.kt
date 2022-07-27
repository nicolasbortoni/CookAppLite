package com.example.cookapplite.RecipeFeature.di

import com.example.cookapplite.LoginFeature.data.UsersRepository
import com.example.cookapplite.RecipeFeature.data.RecipesRepository
import com.example.cookapplite.RecipeFeature.data.RecipeDataSource
import com.example.cookapplite.RecipeFeature.framework.RecipeDataSourceImpl
import com.example.cookapplite.RecipeFeature.data.RecipeStorage
import com.example.cookapplite.RecipeFeature.framework.RecipeStorageImpl
import com.example.cookapplite.RecipeFeature.ui.viewmodel.AddRecipeViewModel
import com.example.cookapplite.RecipeFeature.ui.viewmodel.DetailRecipeViewModel
import com.example.cookapplite.RecipeFeature.ui.viewmodel.ProfileViewModel
import com.example.cookapplite.RecipeFeature.ui.viewmodel.RecipeListViewModel
import com.example.cookapplite.RecipeFeature.usecases.*
import com.example.cookapplite.core.usecases.GetSessionData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RecipeDi {

    @Provides
    fun provideRecipeDataSource() : RecipeDataSource = RecipeDataSourceImpl()

    @Provides
    fun provideRecipeStorage() : RecipeStorage = RecipeStorageImpl()

    @Provides
    fun provideRecipesRepository(recipeDataSource: RecipeDataSource, recipeStorage: RecipeStorage) : RecipesRepository = RecipesRepository(recipeDataSource, recipeStorage)

    @Provides
    fun provideCreateRecipe(recipesRepository: RecipesRepository) : CreateRecipe = CreateRecipe(recipesRepository)

    @Provides
    fun provideGetRecipe(recipesRepository: RecipesRepository) : GetRecipesFromRepository = GetRecipesFromRepository(recipesRepository)

    @Provides
    fun provideAddToLikedRecipes(usersRepository: UsersRepository) : AddToLikedRecipes = AddToLikedRecipes(usersRepository)

    @Provides
    fun provideDeleteRecipe(recipesRepository: RecipesRepository) : DeleteRecipe = DeleteRecipe(recipesRepository)

    @Provides
    fun provideSearchRecipes() : SearchRecipes = SearchRecipes()

    @Provides
    fun provideAddRecipeViewModel(createRecipe: CreateRecipe, getSessionData: GetSessionData) : AddRecipeViewModel = AddRecipeViewModel(createRecipe,getSessionData)

    @Provides
    fun provideRecipeListViewModel(getRecipesFromRepository: GetRecipesFromRepository,searchRecipes: SearchRecipes,addToLikedRecipes: AddToLikedRecipes) : RecipeListViewModel = RecipeListViewModel(getRecipesFromRepository, searchRecipes, addToLikedRecipes)

    @Provides
    fun provideProfileViewModel(getSessionData: GetSessionData) : ProfileViewModel = ProfileViewModel(getSessionData)

    @Provides
    fun provideDetailRecipeViewModel(deleteRecipe: DeleteRecipe, getSessionData: GetSessionData) : DetailRecipeViewModel = DetailRecipeViewModel(deleteRecipe,getSessionData)


}