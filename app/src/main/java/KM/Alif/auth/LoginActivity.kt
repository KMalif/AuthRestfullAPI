package KM.Alif.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.auth.databinding.ActivityLoginBinding
import KM.Alif.auth.model.User
import KM.Alif.auth.webService.APIClient
import KM.Alif.auth.webService.Constant
import KM.Alif.auth.webService.SingleResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isLogin()
    }

    override fun onResume() {
        super.onResume()
        tvRegisAction()
        btnLoginAction()
    }

    private fun isLogin(){
        val token = Constant.getToken(this)
        if (!token.equals("UNDEFINED")){
            startActivity(Intent(this, MainActivity::class.java).also { finish() })
        }
        Log.d("Current Token", token)
    }

    private fun tvRegisAction(){
        binding.TVregister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun btnLoginAction(){
        binding.BtnLogin.setOnClickListener {
            doLogin()

        }
    }

    private fun doLogin(){
        val username = binding.EtUsername.text.toString()
        val password = binding.EtPassword.text.toString()

        APIClient.APIEndpoint().login(username, password).enqueue(object : Callback<SingleResponse<User>>{
            override fun onFailure(call: Call<SingleResponse<User>>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<SingleResponse<User>>, response: Response<SingleResponse<User>>) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        Constant.setToken(this@LoginActivity, body.data.token)
                        Toast.makeText(applicationContext, "Hello $body.data.name", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(applicationContext, "Failed to Login. Check Your username or Password", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(applicationContext, "Try Again Later", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}
