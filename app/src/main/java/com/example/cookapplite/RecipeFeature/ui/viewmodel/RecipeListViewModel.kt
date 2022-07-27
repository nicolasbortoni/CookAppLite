package com.example.cookapplite.RecipeFeature.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookapplite.RecipeFeature.domain.Recipe
import com.example.cookapplite.RecipeFeature.ui.NavigatorStates.RecipeListNavigatorStates
import com.example.cookapplite.RecipeFeature.usecases.AddToLikedRecipes
import com.example.cookapplite.RecipeFeature.usecases.GetRecipesFromRepository
import com.example.cookapplite.RecipeFeature.usecases.SearchRecipes
import com.mana.template.core.ui.viewstates.BaseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.mbsoft.givemobile.core.ui.utils.SingleLiveEvent
import kotlinx.coroutines.launch

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    val getRecipesFromRepository: GetRecipesFromRepository,
    val searchRecipes: SearchRecipes,
    val addToLikedRecipes: AddToLikedRecipes
) : ViewModel() {

    private val _navigation = SingleLiveEvent<RecipeListNavigatorStates>()
    val navigation: LiveData<RecipeListNavigatorStates> get() = _navigation

    private val _recipeList : MutableLiveData<MutableList<Recipe>> = MutableLiveData()
    val  recipeList : LiveData <MutableList<Recipe>> get() = _recipeList

    private val _viewState: MutableLiveData<BaseViewState> = MutableLiveData()
    val viewState: LiveData<BaseViewState> get() = _viewState

    init {
        _viewState.value = BaseViewState.Idle
    }

    fun goToAddRecipe(){
        _navigation.value = RecipeListNavigatorStates.toAddRecipeFragment
    }

    fun searchRecipesWithKeyword(keyWord : String){
        viewModelScope.launch {
            _viewState.value = BaseViewState.Loading
            val list = getRecipesFromRepository()
            _recipeList.value = searchRecipes.searchRecipes(keyWord,list).toMutableList()
            _viewState.value = BaseViewState.Idle
        }
    }

    fun likeRecipe(recipe : Recipe){
        viewModelScope.launch {
            addToLikedRecipes(recipe.uid!!)
        }
    }

}