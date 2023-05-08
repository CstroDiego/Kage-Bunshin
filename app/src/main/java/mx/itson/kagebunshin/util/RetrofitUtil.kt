package mx.itson.kagebunshin.util

import com.google.gson.GsonBuilder
import mx.itson.kagebunshin.`interface`.NarutoAPI
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil {
    fun getApi() : NarutoAPI{
        val gson = GsonBuilder().create()
        val retrofit = retrofit2.Retrofit.Builder().baseUrl("http://narutoapi.ddns.net/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(NarutoAPI::class.java)
    }
}