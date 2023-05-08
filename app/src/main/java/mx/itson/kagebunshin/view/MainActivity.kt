package mx.itson.kagebunshin.view

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import mx.itson.kagebunshin.Adapter.NarutoAdapter
import mx.itson.kagebunshin.Adapter.ShinobiAdapter
import mx.itson.kagebunshin.R
import mx.itson.kagebunshin.model.Shinobi
import mx.itson.kagebunshin.util.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: NarutoAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var listaShinobis: ListView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
        initRecyclerView()
        initSwipeRefreshLayout()

    }

    private fun loadData() {
        val api = RetrofitUtil.getApi()
        api.getShinobis().enqueue(object : Callback<List<Shinobi>> {
            override fun onResponse(call: Call<List<Shinobi>>, response: Response<List<Shinobi>>) {
                if (response.isSuccessful) {
                    val shinobiList = response.body()
                    if (shinobiList != null) {
                        shinobiList.forEach {
                            it.nombre?.let { it1 ->
                                Log.i("Shinobi", it1)
                                Log.i("Shinobi", "Hola?")
                            }
                        }
                        adapter.updateList(shinobiList)
                        swipeRefreshLayout.isRefreshing = false
                    }
                } else {

                    Log.i("Shinobi", "Error")
                }
            }

            override fun onFailure(call: Call<List<Shinobi>>, t: Throwable) {
                Log.e("Error", t.toString())
            }
        })
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvShinobis)
        val shinobis = ArrayList<Shinobi>()
        adapter = NarutoAdapter(shinobis)
        val menu = ShinobiAdapter(this, shinobis)
        listaShinobis?.adapter = menu
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }
    }
}

