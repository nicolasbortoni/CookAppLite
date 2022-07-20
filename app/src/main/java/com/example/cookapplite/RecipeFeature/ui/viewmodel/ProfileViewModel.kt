package com.example.cookapplite.RecipeFeature.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookapplite.LoginFeature.domain.User
import com.example.cookapplite.RecipeFeature.ui.NavigatorStates.ProfileNavigatorStates
import com.example.cookapplite.RecipeFeature.ui.NavigatorStates.RecipeListNavigatorStates
import com.example.cookapplite.core.usecases.GetSessionData
import com.mbsoft.givemobile.core.ui.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val getSessionData : GetSessionData
) : ViewModel() {

    private val _navigation = SingleLiveEvent<ProfileNavigatorStates>()
    val navigation: LiveData<ProfileNavigatorStates> get() = _navigation

    fun getProfileData() : User {
       return getSessionData()
    }

}