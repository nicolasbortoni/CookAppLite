package com.example.cookapplite.RecipeFeature.framework

import android.net.Uri
import com.example.cookapplite.RecipeFeature.domain.Recipe

interface RecipeStorage {

    suspend fun saveRecipeImage(image : Uri?) : String?

}