package com.example.cookapplite.LoginFeature.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookapplite.LoginFeature.domain.User
import com.example.cookapplite.LoginFeature.ui.navigatorStates.AddUserNavigatorStates
import com.example.cookapplite.LoginFeature.ui.navigatorStates.LoginNavigatorStates
import com.example.cookapplite.LoginFeature.usecases.CreateUser
import com.example.cookapplite.LoginFeature.usecases.GetUserLoged
import com.example.cookapplite.core.usecases.SetSessionData
import com.mbsoft.givemobile.core.ui.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    val createUser : CreateUser,
    val setSessionData: SetSessionData,
    val getUserLoged: GetUserLoged
) : ViewModel() {

    private val _navigation = SingleLiveEvent<AddUserNavigatorStates>()
    val navigation: LiveData<AddUserNavigatorStates> get() = _navigation

    fun createNewUser(newUser: User, newPass : String, imageUri : Uri?){

        viewModelScope.launch {
            if(createUser(newUser, newPass, imageUri)){
                val userLoged = getUserLoged()
                setSessionData(userLoged)
                _navigation.value = AddUserNavigatorStates.ToMainActivity
            }
        }

    }

}