package com.example.flixterplus

import com.google.gson.annotations.SerializedName

class tvShow {
    @SerializedName("original_title")
    var original_title = ""

    @SerializedName("overview")
    var overview = ""

    @SerializedName("poster_path")
    var poster_path = ""

    @SerializedName("release_date")
    var release_date = ""
}
