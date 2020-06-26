package io.github.rosariopfernandes.minibrothereye.util

sealed class DataResult <out T : Any> {
    data class Success<out T : Any>(val data: T) : DataResult<T>()
    data class Error(val exception: Exception) : DataResult<Nothing>()
    object InProgress : DataResult<Nothing>()
}