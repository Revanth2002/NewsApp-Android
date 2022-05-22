package najah.dev.news_app.typecode

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import najah.dev.news_app.R
import najah.dev.news_app.typecode.models.TypeCodeRest
import najah.dev.news_app.typecode.repository.TypeCodeRepository

class TypeCodeMainActivity : AppCompatActivity() {

    private lateinit var  viewModel: TypeCodeMainViewModel

    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.typecodeactivity)

        val repository = TypeCodeRepository()
        val viewModelFactory = TypecodeMainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(TypeCodeMainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer {
            response ->
            if(response.isSuccessful){
                //Log.d("Response",response.body()?.myUserId.toString())
            }else{
                //Log.d("Response",response.errorBody().toString())
            }
        })


        //STEP-4 : POST - MainActivity
        val myPost = TypeCodeRest(2,2,"Testing title","developer")
        viewModel.pushPost(myPost)
        viewModel.myResponse.observe(this, Observer {
            response ->
            if(response.isSuccessful){
                Log.d("Main",response.body().toString())
                Log.d("Main",response.code().toString())

            }
        })

    }

}