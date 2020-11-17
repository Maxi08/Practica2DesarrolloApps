package com.example.weightlost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.weightlost.clases.Usuarios
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_progress.*

class LoginActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Login()

        textViewRegistro.setOnClickListener(){
            val intent=Intent(this,MainActivity::class.java).apply {  }
            startActivity(intent)
        }
    }

    private fun Login(){

        title="Iniciar sesión"

        Login.setOnClickListener{
            if(editTextEmailLogin.text.isNotEmpty() && editTextPasswordLogin.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(editTextEmailLogin.text.toString(),
                    editTextPasswordLogin.text.toString()).addOnCompleteListener{

                    if(it.isSuccessful){
                        val id=(it.result?.user?.uid?:"")

                        showProgressActivity(id);

                        this.finish()

                    }
                    else{
                        showAlert()

                    }

                }
            }

        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error al intentar iniciar la sesion del usuario,verifique que los campos han sido añadidos correctamente")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }






    private fun showProgressActivity(id:String) {

        val intent = Intent(this, ProgressActivity::class.java)

        startActivity(intent)
        this.finish()




        }


    }






