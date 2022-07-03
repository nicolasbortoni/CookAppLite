package com.example.cookapplite.LoginFeature.data

import android.net.Uri

interface UserStorage {
    suspend fun saveProfileImage(image : Uri?, uid : String) : String?
}