package com.mana.template.core.ui.viewstates

sealed class DataViewState {
    object Idle: DataViewState()
    object Loading: DataViewState()
    object Refreshing: DataViewState()
    data class Failure(val exception: Exception): DataViewState()
    data class Alert(val title: Int, val message: Int): DataViewState()
}