package com.example.cookapplite.RecipeFeature.framework.entities

import com.example.cookapplite.RecipeFeature.domain.Recipe

class FirebaseRecipe (
    val uid : String,
    val ownerUid : String,
    val author : String,
    val description : String,
    val title : String,
    val imagePath : String,
    val recipe :String
    ){
    constructor():this ("","","","","","","")
}

//fun Recipe.toFirebaseRecipe() : FirebaseRecipe = FirebaseRecipe(title!!,description!!)
fun FirebaseRecipe.toRecipe() : Recipe = Recipe(uid,ownerUid, title, author, recipe, imagePath, description)