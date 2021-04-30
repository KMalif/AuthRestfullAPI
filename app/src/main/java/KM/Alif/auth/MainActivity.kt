package KM.Alif.auth

import KM.Alif.auth.adapter.MainAdapter
import KM.Alif.auth.model.Barang
import KM.Alif.auth.webService.APIClient
import KM.Alif.auth.webService.Constant
import KM.Alif.auth.webService.ListResponse
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.auth.R
import com.example.auth.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var mainAdapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getAllbarang()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.icLogout -> logout()
            }
            true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout(){
        Constant.clearToken(this)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
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
                        showStufftoRecycler(body.data)
                    }
                }
            }
        })
    }

    private fun showStufftoRecycler(stuff : List<Barang>){
        mainAdapter = MainAdapter(stuff)
        binding.RvBarang.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}