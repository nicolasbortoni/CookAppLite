package com.example.cookapplite.RecipeFeature.usecases

import com.example.cookapplite.LoginFeature.data.UsersRepository

class AddToLikedRecipes(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(recipeUid : String) = usersRepository.addToLikedRecipes(recipeUid)
}