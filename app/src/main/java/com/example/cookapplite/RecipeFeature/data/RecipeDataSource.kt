package com.example.cookapplite.RecipeFeature.data

import com.example.cookapplite.RecipeFeature.domain.Recipe

interface RecipeDataSource {
    suspend fun addRecipe(newRecipe : Recipe) : Boolean
    suspend fun getAll() : List<Recipe>
    suspend fun removeRecipe(uid : String) : Boolean
    suspend fun searchRecipe(keyword : String) : List<Recipe>
}