package com.example.cookapplite.RecipeFeature.data

import android.net.Uri
import com.example.cookapplite.RecipeFeature.domain.Recipe

class RecipesRepository constructor(
    private val recipeDataSource : RecipeDataSource,
    private val recipeStorage : RecipeStorage
)
{
    suspend fun createRecipe(newRecipe : Recipe, recipeImage : Uri?) : Boolean{
        val imagePath = recipeStorage.saveRecipeImage(recipeImage)
        newRecipe.image = imagePath
        return recipeDataSource.addRecipe(newRecipe)
    }

    suspend fun getRecipes() : List<Recipe>{
        return recipeDataSource.getAll()
    }

}