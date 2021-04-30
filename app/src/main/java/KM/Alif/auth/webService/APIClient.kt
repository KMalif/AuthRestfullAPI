package KM.Alif.auth.webService

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient {
    companion object{
        private var retrofit : Retrofit? = null
        private var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        fun APIEndpoint(): APIEndpoint = getClient()
            .create(APIEndpoint::class.java)

        private fun getClient(): Retrofit {
            return if (retrofit == null){
                retrofit = Retrofit.Builder().baseUrl(Constant.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                retrofit!!
            }else{
                retrofit!!
            }
        }
    }
}

class Constant{
    companion object{
        const val BASE_URL = "https://apibarang.herokuapp.com/"

        fun setToken(context: Context, token : String){
            val shared = context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
            shared.edit().apply {
                putString("TOKEN", token)
                apply()
            }
        }

        fun getToken(context: Context): String{
            val shared = context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
            val token = shared.getString("TOKEN", "UNDEFINED")
            return token!!
        }

        fun clearToken(context: Context){
            val shared = context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
            shared.edit().clear().apply()
        }
    }
}