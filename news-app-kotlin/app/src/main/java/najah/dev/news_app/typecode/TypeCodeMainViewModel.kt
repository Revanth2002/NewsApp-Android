package najah.dev.news_app.typecode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import najah.dev.news_app.typecode.models.TypeCodeRest
import najah.dev.news_app.typecode.repository.TypeCodeRepository
import retrofit2.Response
import retrofit2.http.POST

class TypeCodeMainViewModel(private val repository: TypeCodeRepository) : ViewModel(){

    val myResponse : MutableLiveData<Response<TypeCodeRest>> = MutableLiveData()
    fun getPost(){
        viewModelScope.launch {
            val response : Response<TypeCodeRest> = repository.getPosts()
            myResponse.value = response
        }
    }


    //STEP-3 : POST - View Model
    fun pushPost(post: TypeCodeRest){
        viewModelScope.launch {
            val response : Response<TypeCodeRest> = repository.pushPost(post)
            myResponse.value = response
        }
    }


}