package com.example.challenge8.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseLogin (
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username : String,
    @SerializedName("password")
    val password : String
) : Parcelable