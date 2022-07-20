package com.mana.template.core.ui.viewstates

sealed class EditViewState {
    object Idle: EditViewState()
    object Loading: EditViewState()
    object Editing: EditViewState()
    data class Failure(val exception: Exception): EditViewState()
    data class Alert(val title: Int, val message: Int, val callback: (() -> Unit)? = null): EditViewState()
}