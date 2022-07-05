package com.example.cookapplite.RecipeFeature.framework

import com.example.cookapplite.RecipeFeature.domain.Recipe
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.Exception
import javax.inject.Inject

class RecipeDataSourceImpl @Inject constructor() : RecipeDataSource {

    private val db = Firebase.firestore
    private val auth = Firebase.auth

    override suspend fun addRecipe(newRecipe: Recipe): Boolean {

        newRecipe.uid = auth.currentUser?.uid

        var result = try {
            db
                .collection("Recipes")
                .document(newRecipe.uid.toString())
                .set(newRecipe)
            true
        } catch (e : Exception){
            false
        }
        return result
    }

}