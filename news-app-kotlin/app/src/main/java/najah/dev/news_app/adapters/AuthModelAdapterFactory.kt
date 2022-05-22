package najah.dev.news_app.adapters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import najah.dev.news_app.repository.AuthRepository

class AuthModelAdapterFactory (private val repository: AuthRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  AuthModelAdapter(repository) as T
    }

}