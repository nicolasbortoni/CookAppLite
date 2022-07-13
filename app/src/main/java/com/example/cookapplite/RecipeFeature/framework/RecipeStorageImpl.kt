package com.example.cookapplite.RecipeFeature.framework

import android.net.Uri
import com.example.cookapplite.RecipeFeature.data.RecipeStorage
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class RecipeStorageImpl @Inject constructor() : RecipeStorage {

    private val storage = Firebase.storage
    var storageRef = storage.reference

    override suspend fun saveRecipeImage(recipeImage : Uri?) : String?{
        val recipesImageRef = storageRef.child("recipesImages").child(UUID.randomUUID().toString())

        return try {
            recipesImageRef
                .putFile(recipeImage as Uri)
                .await()
                recipesImageRef.downloadUrl.await().toString()
        }catch (e : Exception) {
            null
        }

    }

}