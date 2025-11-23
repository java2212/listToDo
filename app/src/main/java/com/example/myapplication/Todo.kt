package com.example.myapplication

data class Todo (
    val id: Long = System.currentTimeMillis(),
    val title: String,
    var isChecked: Boolean = false,
)