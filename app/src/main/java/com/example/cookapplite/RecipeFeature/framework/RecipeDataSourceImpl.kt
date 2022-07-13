package com.example.cookapplite.RecipeFeature.framework

import android.util.Log
import com.example.cookapplite.RecipeFeature.data.RecipeDataSource
import com.example.cookapplite.RecipeFeature.domain.Recipe
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

    override suspend fun getAll(): MutableList<Recipe> {

        val instrumentList = mutableListOf<Recipe>()

        val query = db.collection("Recipes")

        try {
            val data = query.get().await()
            for (document in data) {
                instrumentList.add(document.toObject())
            }
        } catch (e: Exception) {
            Log.d("TEST",e.toString())
        }
        return instrumentList

    }

}