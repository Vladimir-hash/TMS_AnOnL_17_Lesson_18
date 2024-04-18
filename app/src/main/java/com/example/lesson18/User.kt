package com.example.lesson18

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


    @Parcelize
    data class User(
        val login: String = "",
        val password: String = "",
        val color: String = "",
        val options: List<String>,
    ) : Parcelable


