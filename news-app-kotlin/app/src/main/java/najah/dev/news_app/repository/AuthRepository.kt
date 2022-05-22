package najah.dev.news_app.repository

import najah.dev.news_app.api.DjangoRetrofitInstance
import najah.dev.news_app.models.Auth
import retrofit2.Response

class AuthRepository {

    //Login Method
    suspend fun performLogin(data: Auth): Response<Auth> {
        return DjangoRetrofitInstance.api.performLogin(data)
    }

    //Register Method
    suspend fun performRegister(data: Auth): Response<Auth> {
        return DjangoRetrofitInstance.api.performRegister(data)
    }

    //Verify OTP Method
    suspend fun performVerification(data: Auth): Response<Auth> {
        return DjangoRetrofitInstance.api.performVerification(data)
    }

}