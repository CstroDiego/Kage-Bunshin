package mx.itson.kagebunshin.`interface`

import retrofit2.Call
import mx.itson.kagebunshin.model.Shinobi
import retrofit2.http.GET

interface NarutoAPI {

    @GET("naruto")
    fun getShinobis(): Call<List<Shinobi>>
}