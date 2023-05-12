package mx.itson.kagebunshin.`interface`

import retrofit2.Call
import mx.itson.kagebunshin.model.Shinobi
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface NarutoAPI {

    @GET("naruto")
    fun getShinobis(): Call<List<Shinobi>>

    @DELETE("naruto/{id}")
    fun deleteShinobi(id: Int): Call<Void>

    @POST("naruto")
    fun addShinobi(shinobi: Shinobi): Call<Shinobi>

    @PUT("naruto/{id}")
    fun updateShinobi(@Path("id") id: Int?, @Body shinobi: Shinobi): Call<Shinobi>


}