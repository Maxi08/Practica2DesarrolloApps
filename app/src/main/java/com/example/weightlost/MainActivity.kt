package com.example.weightlost

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_boarding_original.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registrar()

        textView6.setOnClickListener(){
            val intent=Intent(this,LoginActivity::class.java).apply {  }
            startActivity(intent)
        }

    }

    private fun registrar(){

        title="Registrar usuario"

        registrate.setOnClickListener{
            if(editTextEmail.text.isNotEmpty() && editTextPassword.text.isNotEmpty() &&editTextTextPersonName.text.isNotEmpty()
                && editTextTextPersonLatName.text.isNotEmpty()){
                 FirebaseAuth.getInstance().createUserWithEmailAndPassword(editTextEmail.text.toString(),
                     editTextPassword.text.toString()).addOnCompleteListener{

                     if(it.isSuccessful){
                         val id=it.result?.user?.uid?:""
                         val nombre=editTextTextPersonName.text.toString()
                         val apellido=editTextTextPersonLatName.text.toString()

                         showName(id,nombre,apellido);
                         this.finish()

                     }
                     else{
                         Toast.makeText(this,"No deje campos vacios", Toast.LENGTH_LONG).show()
                         showAlert()

                     }

                 }
            }

         }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error al registrar al usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog:AlertDialog= builder.create()
        dialog.show()
    }

    private fun showName(id:String,name:String,apellido:String){
        val intent= Intent(this, BoardingActivityOriginal::class.java)
            intent.putExtra("nombre",name)
            intent.putExtra("apellido",apellido)
            intent.putExtra("id",id)
            startActivity(intent)
            this.finish()

       }



    }



