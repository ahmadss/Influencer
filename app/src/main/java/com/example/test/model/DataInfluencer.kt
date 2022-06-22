package com.example.test.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DataInfluencer : Serializable {
    @SerializedName("id")
    var id = 0

    @SerializedName("username")
    var username: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("followers")
    var followers = 0

    @SerializedName("engagement_rate")
    var engagement_rate = 0

    @SerializedName("avg_likes")
    var avg_likes = 0

    @SerializedName("avg_comments")
    var avg_comments = 0

    @SerializedName("contactperson")
    var contactperson: String? = null
}