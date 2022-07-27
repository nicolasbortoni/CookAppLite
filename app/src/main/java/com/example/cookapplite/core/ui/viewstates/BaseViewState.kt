package com.mana.template.core.ui.viewstates

import com.example.cookapplite.RecipeFeature.domain.Recipe

sealed class BaseViewState {
    object Idle: BaseViewState()
    object Loading: BaseViewState()
    data class Failure(val exception: Exception): BaseViewState()
    data class Alert(val title: String, val message: String, val callback: (() -> Unit)? = null): BaseViewState()
}