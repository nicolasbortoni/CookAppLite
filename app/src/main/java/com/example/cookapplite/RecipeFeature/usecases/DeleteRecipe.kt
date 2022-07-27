package com.example.cookapplite.RecipeFeature.usecases

import com.example.cookapplite.RecipeFeature.data.RecipesRepository

class DeleteRecipe(private val recipesRepository: RecipesRepository) {
    suspend operator fun invoke(recipeUid : String) = recipesRepository.deleteRecipe(recipeUid)
}