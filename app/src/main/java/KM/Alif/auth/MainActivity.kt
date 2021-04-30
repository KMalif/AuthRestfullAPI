package KM.Alif.auth

import KM.Alif.auth.model.Barang
import KM.Alif.auth.webService.APIClient
import KM.Alif.auth.webService.ListResponse
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.auth.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAllbarang()
    }

    private fun getAllbarang(){
        APIClient.APIEndpoint().getAllbarang().enqueue(object : Callback<ListResponse<Barang>>{
            override fun onFailure(call: Call<ListResponse<Barang>>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<ListResponse<Barang>>, response: Response<ListResponse<Barang>>) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        Log.d("AllBarang", "onResponse: ${body.data}")
                    }
                }
            }
        })
    }
}