package najah.dev.news_app.models

import com.google.gson.annotations.SerializedName

data class Auth (

    @SerializedName("auth")
    val message:String,
    val otp:String,
    val phonenumber:String,
    val token: String,
    val name:String
)