package com.example.weightlost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weightlost.clases.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_progress.*
import java.util.EnumSet.of

class ProgressActivity : AppCompatActivity() {
   // private  val viewModel by lazy {ViewModelProviders.of(this).get(MainViewModel::class.java)}
    private lateinit var adapter:MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        adapter= MainAdapter(this)

        recyclerView.layoutManager =LinearLayoutManager(this)
        recyclerView.adapter= adapter
        //observeData()
        FirebaseService().getPesosById(FirebaseAuth.getInstance().currentUser!!.uid, object:
            FirebaseService.IResult<List<Pesos>> {
            override fun onSuccess(items: List<Pesos>?) {
                if (items != null) {
                    adapter.setListData(items)
                }

            }

            override fun onError(exception: Exception) {

            }

        } )

        title = "Progreso"

        mostrarPeso()

        add.setOnClickListener(){
            val intent= Intent(this, ProgressRegisterActivity::class.java)
            //intent.putExtra("id",id)
            startActivity(intent)
        }




    }
    //fun observeData(){
//        viewModel.fetchPesoData().observe(this, Observer {
//            adapter.setListData(it)
//            adapter.notifyDataSetChanged()
//        })
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater =menuInflater
        inflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.cerrarSesion->
                salir()


        }

        return super.onOptionsItemSelected(item)
    }
    fun salir(){
//        FirebaseAuth.getInstance().signOut()
//        onBackPressed()
        val intent= Intent(this, LoginActivity::class.java)

        startActivity(intent)
        this.finish()

    }
    private fun mostrarPeso() {



        FirebaseService().getUserById(FirebaseAuth.getInstance().currentUser!!.uid, object :
            FirebaseService.IResult<Usuarios> {
            override fun onSuccess(items: Usuarios?) {
                editTextOriginal.text = items?.pesoActual.toString()
                editTextObjetivo.text = items?.pesoObjetivo.toString()
                editTextRestante.text=(items?.pesoActual?.minus(items?.pesoObjetivo!!)).toString()
            }

            override fun onError(exception: Exception) {

            }
        })


    }}
