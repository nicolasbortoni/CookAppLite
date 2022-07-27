package com.example.cookapplite.RecipeFeature.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookapplite.LoginFeature.usecases.GetUserLoged
import com.example.cookapplite.RecipeFeature.data.RecipesRepository
import com.example.cookapplite.RecipeFeature.domain.Recipe
import com.example.cookapplite.RecipeFeature.ui.NavigatorStates.AddRecipeNavigatorStates
import com.example.cookapplite.RecipeFeature.ui.NavigatorStates.DetailRecipeNavigatorStates
import com.example.cookapplite.RecipeFeature.ui.NavigatorStates.RecipeListNavigatorStates
import com.example.cookapplite.RecipeFeature.usecases.DeleteRecipe
import com.example.cookapplite.core.usecases.GetSessionData
import com.google.firebase.firestore.auth.User
import com.mana.template.core.ui.viewstates.BaseViewState
import com.mbsoft.givemobile.core.ui.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailRecipeViewModel @Inject constructor(
    val deleteRecipe: DeleteRecipe,
    val getSessionData: GetSessionData
) : ViewModel() {

    private val _navigation = SingleLiveEvent<DetailRecipeNavigatorStates>()
    val navigation: LiveData<DetailRecipeNavigatorStates> get() = _navigation

    private val _viewState: MutableLiveData<BaseViewState> = MutableLiveData()
    val viewState: LiveData<BaseViewState> get() = _viewState

    fun deleteRecipe(recipe:Recipe){
        _viewState.value = BaseViewState.Alert("Delete recipe?","This recipe will be eliminated") {  deleteItem(recipe) }
    }

    fun  getUserData() : com.example.cookapplite.LoginFeature.domain.User {
        return getSessionData()
    }

    private fun deleteItem(recipe: Recipe){
        viewModelScope.launch {
            if(deleteRecipe(recipe.uid!!)){
                _navigation.value = DetailRecipeNavigatorStates.popBack
            }
        }
    }

}