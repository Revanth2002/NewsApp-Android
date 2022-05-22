package najah.dev.news_app.typecode.repository

import najah.dev.news_app.api.RetrofitInstance
import najah.dev.news_app.typecode.api.TypeCodeRetrofitInstance
import najah.dev.news_app.typecode.models.TypeCodeRest
import retrofit2.Response
import retrofit2.http.POST

class TypeCodeRepository {

    suspend fun getPosts() : Response<TypeCodeRest> {
        return TypeCodeRetrofitInstance.api.getPosts()
    }

    //STEP-2 : POST - Repository
    suspend fun pushPost(post: TypeCodeRest) : Response<TypeCodeRest>{
        return TypeCodeRetrofitInstance.api.pushPost(post)
    }
}