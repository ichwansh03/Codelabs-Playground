package com.ichwan.gigihmodule.retrofit

import com.google.gson.annotations.SerializedName

data class ResponseModel (
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    var username: String,
    var email: String
)