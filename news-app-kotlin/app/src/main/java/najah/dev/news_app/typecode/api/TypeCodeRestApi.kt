//Creating an APi interface with endpoints and mapping to the models

package najah.dev.news_app.typecode.api

import najah.dev.news_app.typecode.models.TypeCodeRest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TypeCodeRestApi {

    @GET("posts/1")
    suspend fun getPosts(): Response<TypeCodeRest>


    //STEP-1 : POST - API
    @POST("posts")
    suspend fun pushPost(
        @Body post: TypeCodeRest
    ) : Response<TypeCodeRest>

}