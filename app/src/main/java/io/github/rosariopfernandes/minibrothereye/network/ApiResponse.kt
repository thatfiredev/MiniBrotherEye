package io.github.rosariopfernandes.minibrothereye.network

import io.github.rosariopfernandes.minibrothereye.model.Character

data class ApiResponse(
    val characters: List<Character>,
    val next_cursor: Int,
    val previous_cursor: Int
)