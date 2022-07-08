package com.example.cookapplite.RecipeFeature.framework

import com.example.cookapplite.RecipeFeature.domain.Recipe

interface RecipeDataSource {
    suspend fun addRecipe(newRecipe : Recipe) : Boolean
    suspend fun getAll() : List<Recipe>
}