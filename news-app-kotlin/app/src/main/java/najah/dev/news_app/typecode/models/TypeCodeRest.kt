//Create models with all the present values in rest GET method

package najah.dev.news_app.typecode.models

import com.google.gson.annotations.SerializedName

data  class TypeCodeRest (

    @SerializedName("userId")
    val myUserId:Int,
    val id:Int,
    val title:String,
    val body:String
    )