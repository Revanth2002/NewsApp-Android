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

class RegisterActivity : AppCompatActivity() {

    lateinit var viewModel: AuthModelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_page)

        //Login Page
        goToLoginPage()

        //Otp send
        getOtpVerification()
    }

    private fun goToLoginPage(){
        val gotoLoginBtn : TextView = findViewById(R.id.gotologinbtn)
        gotoLoginBtn.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getOtpVerification(){
        val registerBtn = findViewById<Button>(R.id.register_button)
        val userName = findViewById<EditText>(R.id.username)
        val mobileNumber = findViewById<EditText>(R.id.phonenumber)

        registerBtn.setOnClickListener{
            if(userName.text.isNotEmpty()){
                if(mobileNumber.text.length == 10){
                    //Perform POST Verification for OTP
                    Log.d("Main Number",mobileNumber.text.toString())
                    val repository = AuthRepository()
                    val viewModelFactory = AuthModelAdapterFactory(repository)
                    viewModel = ViewModelProvider(this,viewModelFactory).get(AuthModelAdapter::class.java)

                    //Make a post to server and send otp verification
                    val registerData = Auth("message","0000",mobileNumber.text.toString(),"token",userName.text.toString())
                    viewModel.performRegisterPost(registerData)
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
                            Toast.makeText(this,"User already exists.Please Try login",Toast.LENGTH_LONG).show()
                        }
                    })
                }else{
                    //Improper phone number
                    Toast.makeText(this,"Enter a proper 10 digit phone number",Toast.LENGTH_LONG).show()
                }
            }else{
                //Improper username
                Toast.makeText(this,"Username cannot be empty",Toast.LENGTH_LONG).show()
            }
        }
    }


}