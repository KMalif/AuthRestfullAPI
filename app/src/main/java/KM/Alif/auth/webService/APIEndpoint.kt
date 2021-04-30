package KM.Alif.auth.webService
import KM.Alif.auth.model.Barang
import KM.Alif.auth.model.User
import retrofit2.Call
import retrofit2.http.*

interface APIEndpoint {

    @FormUrlEncoded
    @POST("auth/sign-up")
    fun register (
        @Field("name")name : String,
        @Field("username")username : String,
        @Field("email")email : String,
        @Field("password")password : String): Call<SingleResponse<User>>

    @FormUrlEncoded
    @POST("auth/sign-in ")
    fun login(
        @Field("username") username: String,
        @Field("password")password: String
    ): Call<SingleResponse<User>>

    @GET("barang")
    fun getAllbarang():Call<ListResponse<Barang>>

}

data class ListResponse<T>(
    var msg : String,
    var status : Int,
    var data : List<T>
)

data class SingleResponse<T>(
    var msg : String,
    var status : Int,
    var data : T
)