package com.example.cookapplite.RecipeFeature.framework

import com.example.cookapplite.RecipeFeature.domain.Recipe
import com.example.cookapplite.RecipeFeature.framework.entities.FirebaseRecipe
import com.example.cookapplite.RecipeFeature.framework.entities.toRecipe
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
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
                .document()
                .set(newRecipe)
                .await()
            true
        } catch (e : Exception){
            false
        }
        return result
    }

    override suspend fun getRecipes(): List<Recipe> {
        var list : MutableList<Recipe> = mutableListOf()
        return try {
            val data = db
                .collection("Recipes")
                .get()
                .await()
            for (document in data){
                list.add(document.toObject<FirebaseRecipe>().toRecipe())
            }
            list
        } catch (e : Exception){
            emptyList()
        }
    }

}