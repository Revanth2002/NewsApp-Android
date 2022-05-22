package najah.dev.news_app.typecode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import najah.dev.news_app.typecode.repository.TypeCodeRepository

class TypecodeMainViewModelFactory (private val repository: TypeCodeRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  TypeCodeMainViewModel(repository) as T
    }

}