package com.example.cookapplite.LoginFeature.domain

import android.net.Uri
import com.example.cookapplite.RecipeFeature.domain.Recipe
import java.util.*

data class User (
    var uuid : String?,
    var username : String?,
    var email : String?,
    var phone : String?,
    var birthday : String?,
    var profileImage : String?,
    var likedRecipes : MutableList<String>,
    var userRecipes : MutableList<String>
)
{
    constructor():this ("","","","","","", mutableListOf(), mutableListOf())
}