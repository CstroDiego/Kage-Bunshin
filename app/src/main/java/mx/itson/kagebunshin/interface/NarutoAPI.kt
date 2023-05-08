package mx.itson.kagebunshin.`interface`

import android.telecom.Call
import mx.itson.kagebunshin.entity.Shinobi
import retrofit2.http.GET

interface NarutoAPI {

    @GET("naruto")
    fun getShinobis(): Call<List<Shinobi>>
}