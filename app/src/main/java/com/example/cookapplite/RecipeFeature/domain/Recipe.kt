package com.example.cookapplite.RecipeFeature.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Recipe (
    var uid : String?,
    var title : String?,
    var author : String?,
    var recipe : String?,
    var image : String?,
    var description : String?
) : Parcelable

{
    constructor():this ("","","","","","")
}