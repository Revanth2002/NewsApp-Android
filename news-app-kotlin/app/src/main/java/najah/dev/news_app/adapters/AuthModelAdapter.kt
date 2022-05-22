package najah.dev.news_app.adapters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import najah.dev.news_app.models.Auth
import najah.dev.news_app.repository.AuthRepository
import retrofit2.Response

class AuthModelAdapter(private val repository: AuthRepository) : ViewModel() {
    val myResponse : MutableLiveData<Response<Auth>> = MutableLiveData()

    fun performLoginPost(data : Auth){
        viewModelScope.launch {
            val response : Response<Auth> = repository.performLogin(data)
            myResponse.value = response
        }
    }

    fun performRegisterPost(data : Auth){
        viewModelScope.launch {
            val response : Response<Auth> = repository.performRegister(data)
            myResponse.value = response
        }
    }

    fun performVerificationPost(data : Auth){
        viewModelScope.launch {
            val response : Response<Auth> = repository.performVerification(data)
            myResponse.value = response
        }
    }

}