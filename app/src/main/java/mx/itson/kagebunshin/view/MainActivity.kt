package mx.itson.kagebunshin.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import mx.itson.kagebunshin.R
import mx.itson.kagebunshin.adapter.ShinobiAdapter
import mx.itson.kagebunshin.model.Shinobi
import mx.itson.kagebunshin.util.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ShinobiAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var shinobis: List<Shinobi>
    private var selectedItemIndex: Int? = null
    private lateinit var listaShinobis: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaShinobis = findViewById(R.id.lvShinobis)

        loadData {
            initList(shinobis)
            initSwipeRefreshLayout()
        }

        // Registrar menú contextual para la lista de shinobis
        registerForContextMenu(listaShinobis)
    }

    private fun loadData(callback: () -> Unit) {
        val api = RetrofitUtil.getApi()
        api.getShinobis().enqueue(object : Callback<List<Shinobi>> {
            override fun onResponse(call: Call<List<Shinobi>>, response: Response<List<Shinobi>>) {
                if (response.isSuccessful) {
                    val shinobiList = response.body()
                    if (shinobiList != null) {
                        shinobis = response.body()!!
                        callback()
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

    private fun initList(shinobiList: List<Shinobi>) {
        shinobis = shinobiList
        adapter = ShinobiAdapter(shinobis)
        listaShinobis.adapter = adapter

        // Registrar menú contextual para la lista de shinobis
        registerForContextMenu(listaShinobis)

        // Función para detectar el click en un item de la lista y mostrar el menú contextual
        listaShinobis.setOnItemClickListener { parent, view, position, id ->
            selectedItemIndex = position
            view.showContextMenu()
        }
    }


    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            loadData {
                initList(shinobis)
                initSwipeRefreshLayout()
            }
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.shinobi_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val shinobiInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val shinobi = listaShinobis.getItemAtPosition(shinobiInfo.position) as Shinobi
        when (item.itemId) {
            R.id.editar -> {
                val intent = Intent(this, ShinobiFormActivity::class.java)
                intent.putExtra("shinobi", shinobi)
                startActivity(intent)
            }

            R.id.eliminar -> {
                val api = RetrofitUtil.getApi()
                shinobi.id?.let {
                    api.deleteShinobi(it).enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            if (response.isSuccessful) {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Shinobi eliminado",
                                    Toast.LENGTH_SHORT
                                ).show()
                                loadData {
                                    initList(shinobis)
                                    initSwipeRefreshLayout()
                                }
                            } else {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Error al eliminar el shinobi",
                                    Toast.LENGTH_SHORT
                                ).show()
                                Log.i("Shinobi", "Error")
                            }
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Log.e("Error", t.toString())
                        }
                    })
                }
            }
        }
        return super.onContextItemSelected(item)
    }
}
