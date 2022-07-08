package com.example.cookapplite.RecipeFeature.di

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
    fun provideAddRecipeViewModel(createRecipe: CreateRecipe) : AddRecipeViewModel = AddRecipeViewModel(createRecipe)

    @Provides
    fun provideRecipeListViewModel(getRecipes: GetRecipesFromRepository) : RecipeListViewModel = RecipeListViewModel(getRecipes)

}