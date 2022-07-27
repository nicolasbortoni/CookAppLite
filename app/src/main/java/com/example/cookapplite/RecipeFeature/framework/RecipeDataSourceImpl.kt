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
import java.util.*
import javax.inject.Inject

class RecipeDataSourceImpl @Inject constructor() : RecipeDataSource {

    private val db = Firebase.firestore

    override suspend fun addRecipe(newRecipe: Recipe): Boolean {

        newRecipe.uid = UUID.randomUUID().toString()

        var result = try {
            db
                .collection("Recipes")
                .document(newRecipe.uid!!)
                .set(newRecipe)
                .await()
            true
        } catch (e : Exception){
            false
        }
        return result
    }

    override suspend fun getAll(): MutableList<Recipe> {

        val recipeList = mutableListOf<Recipe>()

        val query = db.collection("Recipes")

        try {
            val data = query.get().await()
            for (document in data) {
                recipeList.add(document.toObject())
            }
        } catch (e: Exception) {
            Log.d("TEST",e.toString())
        }
        return recipeList

    }

    override suspend fun removeRecipe(uid: String): Boolean {

        val query = db.collection("Recipes").document(uid)

        try {
            query.delete().await()
        }catch (e : Exception){

        }

        return true

    }

    override suspend fun searchRecipe(keyword: String): List<Recipe> {
        val recipeList = mutableListOf<Recipe>()
        val query = db.collection("Recipes")

        return try {
            val data1 = query.whereEqualTo("title",keyword).get().await()
            for(document in data1){
                recipeList.add(document.toObject())
            }
            val data2 = query.whereEqualTo("description", keyword).get().await()
            for(document in data2) {
                recipeList.add(document.toObject())
            }
            recipeList
        }catch (e : Exception){
            emptyList()
        }

    }

}