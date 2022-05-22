package najah.dev.news_app.api

import najah.dev.news_app.models.Auth
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthApi {

    @POST("login/")
    suspend fun performLogin(
        @Body post: Auth
    ) : Response<Auth>

    @POST("register/")
    suspend fun performRegister(
        @Body post: Auth
    ) : Response<Auth>


    @POST("verifyotp/")
    suspend fun performVerification(
        @Body post: Auth
    ) : Response<Auth>


}