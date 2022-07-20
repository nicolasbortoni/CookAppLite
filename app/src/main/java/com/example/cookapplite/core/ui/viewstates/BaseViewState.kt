package com.mana.template.core.ui.viewstates

sealed class BaseViewState {
    object Idle: BaseViewState()
    object Loading: BaseViewState()
    data class Failure(val exception: Exception): BaseViewState()
    data class Alert(val title: Int, val message: Int, val callback: (() -> Unit)? = null): BaseViewState()
}