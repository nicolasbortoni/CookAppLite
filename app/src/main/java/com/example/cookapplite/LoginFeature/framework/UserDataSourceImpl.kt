package com.example.cookapplite.LoginFeature.framework

import android.util.Log
import com.example.cookapplite.LoginFeature.domain.User
import com.example.cookapplite.LoginFeature.data.UserDataSource
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.type.TimeOfDayOrBuilder
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor() : UserDataSource {

    val db = Firebase.firestore

    override suspend fun addUser(newUser: User) : Boolean{
        var result = try {
            db
                .collection("Users")
                .document(newUser.uuid.toString())
                .set(newUser)
            true
        } catch (e : Exception){
            false
        }
        return result
    }

    override suspend fun getUsers(): List<User> {
        return emptyList()
    }

    override suspend fun getUser(uid: String): User {
        val docRef = db.collection("Users").document(uid)
        return try {
            val document = docRef.get().await()
            document.toObject()!!
        }catch (e :Exception){
            Log.e("UserDataSourceImpl", "Exception caught: ${e.message}")
            User("","","","","","", mutableListOf(), mutableListOf())
        }
    }

    override suspend fun updateUserData(user : User) : Boolean{
        val docRef = db.collection("Users").document(user.uuid!!)
        try {
            docRef.set(user).await()
        }catch(e :Exception){
        }
        return true
    }

}