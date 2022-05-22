package najah.dev.news_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import najah.dev.news_app.R
import najah.dev.news_app.adapters.AuthModelAdapter
import najah.dev.news_app.adapters.AuthModelAdapterFactory
import najah.dev.news_app.models.Auth
import najah.dev.news_app.repository.AuthRepository

class VerificationActivity : AppCompatActivity() {

    lateinit var viewModel: AuthModelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.otp_page)

        val mobileNumber=intent.getStringExtra("mobileNumber")

        val otpText : EditText = findViewById(R.id.otp)
        val otpBtn : Button = findViewById(R.id.login_button)

        otpBtn.setOnClickListener{
            if(otpText.text.length != 4){
                Toast.makeText(this,"Provide a valid 4 digit pin",Toast.LENGTH_LONG).show()
            }else{
                //Perform POST Verification for OTP
                val repository = AuthRepository()
                val viewModelFactory = AuthModelAdapterFactory(repository)
                viewModel = ViewModelProvider(this,viewModelFactory).get(AuthModelAdapter::class.java)

                //Make a post to server and send otp verification
                val registerData = Auth("message",otpText.text.toString(),mobileNumber.toString(),"token","username")
                viewModel.performVerificationPost(registerData)
                viewModel.myResponse.observe(this, Observer{
                        response ->
                    if(response.isSuccessful){
                        Log.d("Main-Response-200",response.body()?.otp.toString())
                        Log.d("Main-Response-200",response.code().toString())
                        Log.d("Main-Response-200",response.message().toString())
                        Log.d("Main-Response-200",response.toString())
                        //Toast.makeText(this,response.body().toString(),Toast.LENGTH_LONG).show()
                        val intent = Intent(this, NewsActivity::class.java)
                        intent.putExtra("token",response.body()?.token.toString())
                        startActivity(intent)
                    }else{
                        Log.d("Main-1",response.errorBody().toString())
                        Log.d("Main-2",response.message().toString())
                        Log.d("Main-3",response.toString())
                        Log.d("Main-4",response.code().toString())
                        Toast.makeText(this,"Otp does not match.Try again",Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

    }
}