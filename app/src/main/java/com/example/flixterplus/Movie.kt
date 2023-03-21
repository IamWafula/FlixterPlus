package com.example.flixterplus

import com.google.gson.annotations.SerializedName

public class Movie : java.io.Serializable {
    @SerializedName("original_title")
    var original_title = ""

    @SerializedName("overview")
    var overview = ""

    @SerializedName("poster_path")
    var poster_path = ""

    @SerializedName("release_date")
    var release_date = ""

    @SerializedName("name")
    var name = ""

    @SerializedName("first_air_date")
    var first_air_date = ""
}
