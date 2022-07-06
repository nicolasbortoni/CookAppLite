package com.example.cookapplite.RecipeFeature.framework.entities

import com.example.cookapplite.RecipeFeature.domain.Recipe

class FirebaseRecipe (
    val uid : String,
    val author : String,
    val description : String,
    val title : String,
    val image : String,
    val recipe :String
    ){
    constructor():this ("","","","","","")
}

//fun Recipe.toFirebaseRecipe() : FirebaseRecipe = FirebaseRecipe(title!!,description!!)
fun FirebaseRecipe.toRecipe() : Recipe = Recipe(uid, title, author, recipe, image, description)