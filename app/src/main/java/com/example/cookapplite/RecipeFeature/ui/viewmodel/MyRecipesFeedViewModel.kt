package com.example.cookapplite.RecipeFeature.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cookapplite.RecipeFeature.domain.Recipe
import com.example.cookapplite.RecipeFeature.ui.NavigatorStates.RecipeListNavigatorStates
import com.mana.template.core.ui.viewstates.BaseViewState
import com.mbsoft.givemobile.core.ui.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyRecipesFeedViewModel @Inject constructor(

) : ViewModel() {
    private val _navigation = SingleLiveEvent<RecipeListNavigatorStates>()
    val navigation: LiveData<RecipeListNavigatorStates> get() = _navigation

    private val _recipeList : MutableLiveData<MutableList<Recipe>> = MutableLiveData()
    val  recipeList : LiveData<MutableList<Recipe>> get() = _recipeList

    private val _viewState: MutableLiveData<BaseViewState> = MutableLiveData()
    val viewState: LiveData<BaseViewState> get() = _viewState

    init{
        _viewState.value = BaseViewState.Idle
    }



}