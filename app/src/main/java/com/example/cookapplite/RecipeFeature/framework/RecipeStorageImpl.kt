package com.example.cookapplite.RecipeFeature.framework

import android.net.Uri
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class RecipeStorageImpl @Inject constructor() : RecipeStorage {

    private val storage = Firebase.storage
    private val auth = Firebase.auth
    var storageRef = storage.reference

    override suspend fun saveRecipeImage(recipeImage : Uri?) : String?{
        val profileImageRef = storageRef.child("recipesImages").child(auth.currentUser?.uid!!)

        return try {
            profileImageRef
                .putFile(recipeImage as Uri)
                .await()
            profileImageRef.downloadUrl.toString()
        }catch (e : Exception) {
            null
        }

    }

}