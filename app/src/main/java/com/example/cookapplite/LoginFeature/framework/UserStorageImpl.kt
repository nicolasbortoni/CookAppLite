package com.example.cookapplite.LoginFeature.framework

import android.net.Uri
import com.example.cookapplite.LoginFeature.data.UserStorage
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class UserStorageImpl @Inject constructor() : UserStorage {

    private val storage = Firebase.storage
    var storageRef = storage.reference

    override suspend fun saveProfileImage(image: Uri?, uid : String) : String?{

        val profileImageRef = storageRef.child("profileImages").child(uid)

        return try {
            profileImageRef
                .putFile(image as Uri)
                .await()
            profileImageRef.downloadUrl.await().toString()
        }catch (e : Exception){
            null
        }
    }
}