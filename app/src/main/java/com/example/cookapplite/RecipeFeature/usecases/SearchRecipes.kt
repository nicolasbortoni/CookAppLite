package com.example.cookapplite.RecipeFeature.usecases

import com.example.cookapplite.RecipeFeature.domain.Recipe

class SearchRecipes (){
    fun searchRecipes(keyword : String, recipeList : List<Recipe>) : List<Recipe>{
        val result = mutableListOf<Recipe>()
        recipeList.forEach { recipe ->
            if(recipe.title!!.contains(keyword)){
                result.add(recipe)
            }
            if (recipe.description!!.contains(keyword)){
                result.add(recipe)
            }
        }
        return result
    }
}