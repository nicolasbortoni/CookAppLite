package com.example.cookapplite.RecipeFeature.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookapplite.LoginFeature.domain.User
import com.example.cookapplite.core.usecases.GetSessionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val getSessionData : GetSessionData
) : ViewModel() {

    fun getProfileData() : User {
       return getSessionData()
    }

}