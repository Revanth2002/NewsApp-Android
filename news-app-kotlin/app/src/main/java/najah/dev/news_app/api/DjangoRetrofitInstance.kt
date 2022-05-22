package najah.dev.news_app.api

import najah.dev.news_app.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DjangoRetrofitInstance {

    private val retrofit by lazy{
        Retrofit.Builder().baseUrl(Constants.DJANGO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: AuthApi by lazy {
        retrofit.create(AuthApi::class.java)
    }

}