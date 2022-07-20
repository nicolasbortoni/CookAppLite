package com.example.cookapplite.LoginFeature.ui.navigatorStates

sealed class LoginNavigatorStates{
    object ToAddUserFragment: LoginNavigatorStates()
    object ToMainActivity: LoginNavigatorStates()
}
