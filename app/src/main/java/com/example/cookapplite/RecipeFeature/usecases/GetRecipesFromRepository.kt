package com.example.cookapplite.RecipeFeature.usecases

import android.net.Uri
import com.example.cookapplite.RecipeFeature.data.RecipesRepository
import com.example.cookapplite.RecipeFeature.domain.Recipe

class GetRecipesFromRepository(private val recipesRepository : RecipesRepository) {
    suspend operator fun invoke() = recipesRepository.getRecipes()
}