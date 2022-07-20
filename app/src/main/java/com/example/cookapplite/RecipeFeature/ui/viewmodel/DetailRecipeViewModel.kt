package com.example.cookapplite.RecipeFeature.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cookapplite.RecipeFeature.ui.NavigatorStates.DetailRecipeNavigatorStates
import com.example.cookapplite.RecipeFeature.ui.NavigatorStates.RecipeListNavigatorStates
import com.mbsoft.givemobile.core.ui.utils.SingleLiveEvent

class DetailRecipeViewModel : ViewModel() {

    private val _navigation = SingleLiveEvent<DetailRecipeNavigatorStates>()
    val navigation: LiveData<DetailRecipeNavigatorStates> get() = _navigation

}