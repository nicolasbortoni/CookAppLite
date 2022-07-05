package com.example.cookapplite.RecipeFeature.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookapplite.RecipeFeature.domain.Recipe
import com.example.cookapplite.RecipeFeature.usecases.CreateRecipe
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRecipeViewModel @Inject constructor(
    val createRecipe : CreateRecipe
) : ViewModel() {

    private val _create = MutableLiveData<Boolean?>()
    val create : LiveData<Boolean?> get() = _create

    fun createNewRecipe(newRecipe : Recipe, recipeImage : Uri?){

        viewModelScope.launch {
            _create.value = createRecipe(newRecipe,recipeImage)
        }

    }

}

