package com.example.test.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User : Serializable {
    @SerializedName("user_name")
    var username: String? = null
        get() {
            if (field == null) {
                field = ""
            }
            return field
        }

    @SerializedName("customer_id")
    var customer_id: String? = null
        get() {
            if (field == null) {
                field = ""
            }
            return field
        }

    @SerializedName("customer_type_id")
    var customer_type_id: String? = null
        get() {
            if (field == null) {
                field = ""
            }
            return field
        }

    @SerializedName("customer_name")
    var customer_name: String? = null
        get() {
            if (field == null) {
                field = ""
            }
            return field
        }

    @SerializedName("phone")
    var phone: String? = null
        get() {
            if (field == null) {
                field = ""
            }
            return field
        }

    @SerializedName("email")
    var email: String? = null
        get() {
            if (field == null) {
                field = ""
            }
            return field
        }

    @SerializedName("password")
    var password: String? = null
        get() {
            if (field == null) {
                field = ""
            }
            return field
        }

    @SerializedName("blocked")
    var blocked: String? = null
        get() {
            if (field == null) {
                field = "true"
            }
            return field
        }

    @SerializedName("is_active")
    var is_active: String? = null
        get() {
            if (field == null) {
                field = "false"
            }
            return field
        }
}