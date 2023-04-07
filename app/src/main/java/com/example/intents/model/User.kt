package com.example.emptyactivity.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String,
    val lastName: String,
    val age: Int
) : Parcelable {
    override fun toString(): String {
        return "name= $name,\n lastName= $lastName,\n age=$age"
    }
}