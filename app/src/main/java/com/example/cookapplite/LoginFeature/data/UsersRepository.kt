package com.example.cookapplite.LoginFeature.data

import android.net.Uri
import com.example.cookapplite.LoginFeature.domain.User
import com.example.cookapplite.core.usecases.GetSessionData

class UsersRepository  constructor(
    private val userAuthentication : UserAuthentication,
    private val userDataSource: UserDataSource,
    private val userStorage: UserStorage,
    private val getSessionData: GetSessionData
)
{
    suspend fun createUser(newUser : User, pass : String, imageUri : Uri?) : Boolean{
        val newUid = userAuthentication.createUser(newUser.email.toString(),pass)
        return if (newUid == null){
            false
        } else{
            newUser.uuid = newUid
            newUser.profileImage = userStorage.saveProfileImage(imageUri, newUid)
            userDataSource.addUser(newUser)
            true
        }
    }

    suspend fun loginUser(email : String, pass : String) : Boolean{
        return userAuthentication.login(email, pass)
    }

    suspend fun getUserLoged(): User {
        val currentUid = userAuthentication.getCurrentUid()
        val lala = currentUid
        return userDataSource.getUser(currentUid!!)
    }

    suspend fun addToLikedRecipes(uid : String) : Boolean{
        val user = getSessionData()
        user.likedRecipes.add(uid)
        return userDataSource.updateUserData(user)
    }

}