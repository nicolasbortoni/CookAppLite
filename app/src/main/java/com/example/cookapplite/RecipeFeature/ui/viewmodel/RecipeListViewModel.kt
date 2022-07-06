package com.example.cookapplite.RecipeFeature.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookapplite.RecipeFeature.domain.Recipe
import com.example.cookapplite.RecipeFeature.ui.NavigatorStates.RecipeListNavigatorStates
import com.example.cookapplite.RecipeFeature.usecases.GetRecipesFromRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.mbsoft.givemobile.core.ui.utils.SingleLiveEvent
import kotlinx.coroutines.launch

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    val getRecipesFromRepository: GetRecipesFromRepository
) : ViewModel() {

    private val _navigation = SingleLiveEvent<RecipeListNavigatorStates>()
    val navigation: LiveData<RecipeListNavigatorStates> get() = _navigation

    private val _recipeList : MutableLiveData<MutableList<Recipe>> = MutableLiveData()
    val  recipeList : LiveData <MutableList<Recipe>> get() = _recipeList

    fun goToAddRecipe(){
        _navigation.value = RecipeListNavigatorStates.toAddRecipeFragment
    }

    fun getRecipes(){
        var list : MutableList<Recipe> = mutableListOf()
        viewModelScope.launch {
              _recipeList.value = getRecipesFromRepository() as MutableList<Recipe>
        }
    }

}