package com.muzz.androidchallenge.domain.models

enum class User(val id: Int, val displayName: String) {
    SARAH(1, "Sarah"),
    OTHER(2, "Other User");

    companion object {
        fun fromId(id: Int?): User {
            return entries.firstOrNull { it.id == id } ?: SARAH
        }
    }
}