package najah.dev.news_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import najah.dev.news_app.R
import najah.dev.news_app.adapters.AuthModelAdapter
import najah.dev.news_app.adapters.AuthModelAdapterFactory
import najah.dev.news_app.models.Auth
import najah.dev.news_app.repository.AuthRepository

class LoginActivity : AppCompatActivity() {

    lateinit var viewModel: AuthModelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        //Go to Register Screen
        goToRegisterScreen()

        //Get the OTP verification
        getOtpVerification()

    }

    private fun goToRegisterScreen(){
        val gotoRegisterBtn : TextView = findViewById(R.id.gotoregisterbtn)
        gotoRegisterBtn.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getOtpVerification(){
        val verificationCode = findViewById<Button>(R.id.login_button)
        val mobileNumber = findViewById<EditText>(R.id.phonenumber)

        verificationCode.setOnClickListener {
            if (mobileNumber.text.toString().length != 10) {
                Toast.makeText(this, "Please enter a 10 digit mobile number", Toast.LENGTH_LONG).show()
            } else {
                Log.d("Main Number",mobileNumber.text.toString())
                val repository = AuthRepository()
                val viewModelFactory = AuthModelAdapterFactory(repository)
                viewModel = ViewModelProvider(this,viewModelFactory).get(AuthModelAdapter::class.java)

                //Make a post to server and send otp verification
                val loginData = Auth("message","0000",mobileNumber.text.toString(),"token","name")
                viewModel.performLoginPost(loginData)
                viewModel.myResponse.observe(this, Observer{
                        response ->
                    if(response.isSuccessful){
                        Log.d("Main-Response-200",response.body()?.otp.toString())
                        Log.d("Main-Response-200",response.code().toString())
                        Log.d("Main-Response-200",response.message().toString())
                        Log.d("Main-Response-200",response.toString())
                        //Toast.makeText(this,response.body().toString(),Toast.LENGTH_LONG).show()
                        val intent = Intent(this, VerificationActivity::class.java)
                        intent.putExtra("mobileNumber",response.body()?.phonenumber.toString())
                        startActivity(intent)
                    }else{
                        Log.d("Main-1",response.errorBody().toString())
                        Log.d("Main-2",response.message().toString())
                        Log.d("Main-3",response.toString())
                        Log.d("Main-4",response.code().toString())
                        Toast.makeText(this,"User does not exists.Please Try again",Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }


}