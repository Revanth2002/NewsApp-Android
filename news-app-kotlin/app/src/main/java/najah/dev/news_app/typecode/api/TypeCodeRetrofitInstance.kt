package najah.dev.news_app.typecode.api

import najah.dev.news_app.typecode.utils.TypeCodeConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TypeCodeRetrofitInstance {

    private val retrofit by lazy{
        Retrofit.Builder().baseUrl(TypeCodeConstants.TYPE_CODE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: TypeCodeRestApi by lazy {
        retrofit.create(TypeCodeRestApi::class.java)
    }

}