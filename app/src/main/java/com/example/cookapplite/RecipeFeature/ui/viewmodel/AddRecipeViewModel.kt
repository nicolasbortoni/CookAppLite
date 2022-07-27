package com.example.cookapplite.RecipeFeature.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookapplite.RecipeFeature.domain.Recipe
import com.example.cookapplite.RecipeFeature.ui.NavigatorStates.AddRecipeNavigatorStates
import com.example.cookapplite.RecipeFeature.usecases.CreateRecipe
import com.example.cookapplite.core.usecases.GetSessionData
import com.mana.template.core.ui.viewstates.BaseViewState
import com.mbsoft.givemobile.core.ui.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRecipeViewModel @Inject constructor(
    val createRecipe : CreateRecipe,
    val getSessionData: GetSessionData
) : ViewModel() {

    private val _navigation = SingleLiveEvent<AddRecipeNavigatorStates>()
    val navigation: LiveData<AddRecipeNavigatorStates> get() = _navigation

    private val _viewState = SingleLiveEvent<BaseViewState>()
    val viewState: LiveData<BaseViewState> get() = _viewState

    init {
        _viewState.value = BaseViewState.Loading
        _viewState.value = BaseViewState.Idle
    }

    fun createNewRecipe(newRecipe : Recipe, recipeImage : Uri?){
        viewModelScope.launch {
            _viewState.value = BaseViewState.Loading
            val ownerUid = getSessionData()
            newRecipe.ownerUid = ownerUid.uuid
            val result = createRecipe(newRecipe,recipeImage)
            if(result){
                _navigation.value = AddRecipeNavigatorStates.PopBack
            }
        }
    }


}

