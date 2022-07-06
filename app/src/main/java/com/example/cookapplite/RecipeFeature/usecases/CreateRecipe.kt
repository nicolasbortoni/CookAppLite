package com.example.cookapplite.RecipeFeature.usecases

import android.net.Uri
import com.example.cookapplite.LoginFeature.data.UsersRepository
import com.example.cookapplite.LoginFeature.domain.User
import com.example.cookapplite.RecipeFeature.data.RecipesRepository
import com.example.cookapplite.RecipeFeature.domain.Recipe

class CreateRecipe(private val recipesRepository : RecipesRepository) {
    suspend operator fun invoke(newRecipe: Recipe, recipeImage: Uri?) = recipesRepository.createRecipe(newRecipe, recipeImage)
}