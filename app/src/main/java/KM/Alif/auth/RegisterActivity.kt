package KM.Alif.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.auth.databinding.ActivityRegisterBinding
import KM.Alif.auth.model.User
import KM.Alif.auth.webService.APIClient
import KM.Alif.auth.webService.SingleResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnRegisterAction()
    }

    private fun btnRegisterAction(){
        binding.BtnRegister.setOnClickListener {
            doRegister()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun doRegister(){
        val name = binding.EtName.text.toString()
        val userName = binding.EtUsername.text.toString()
        val email = binding.EtEmail.text.toString()
        val password = binding.EtPassword.text.toString()
        APIClient.APIEndpoint().register(name, userName, email, password).enqueue(object : Callback<SingleResponse<User>>{
            override fun onFailure(call: Call<SingleResponse<User>>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<SingleResponse<User>>, response: Response<SingleResponse<User>>) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        Toast.makeText(applicationContext, body.msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}