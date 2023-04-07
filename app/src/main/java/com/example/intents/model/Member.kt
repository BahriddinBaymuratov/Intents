package com.example.emptyactivity.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Member(
    val salary: Double,
    val name: String
) : Parcelable{
    override fun toString(): String {
        return "salary=$salary,\n name= $name "
    }
}