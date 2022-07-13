package com.example.cookapplite.LoginFeature.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookapplite.LoginFeature.usecases.GetUserLoged
import com.example.cookapplite.LoginFeature.usecases.LoginUser
import com.example.cookapplite.core.domain.SessionData
import com.example.cookapplite.core.usecases.SetSessionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val loginUser : LoginUser,
    val setSessionData: SetSessionData,
    val getUserLoged : GetUserLoged
): ViewModel() {

    private val _login = MutableLiveData<Boolean?>()
    val login : LiveData<Boolean?> get() = _login

    fun loginUsers(email : String,pass : String){
        viewModelScope.launch {
            _login.value = loginUser(email,pass)
            val userLoged = getUserLoged()
            setSessionData(userLoged)
        }
    }

}