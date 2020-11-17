package com.example.weightlost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_boarding_original.*
import kotlinx.android.synthetic.main.activity_main.*

class BoardingActivityOriginal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boarding_original)
        title="Progreso"

        var intent=intent
        val id=intent.getStringExtra("id")
        val nombre=intent.getStringExtra("nombre")
        val apellido=intent.getStringExtra("apellido")
        saludo.text="Hola, ${nombre}"
        Toast.makeText(this,"Debe llenar esta parte del formulario " +
                "para completar el registro de su nuevo usuario," +
                "sino no se guardara los registros anteriores",Toast.LENGTH_LONG).show()




        Continuar.setOnClickListener(){
                if(id !=null && nombre !=null && apellido !=null){
                agregarPeso(id,nombre,apellido)

                }
                else{
                    Toast.makeText(this,"No deje campos vacios", Toast.LENGTH_SHORT).show()
                }


            }


    }

    public fun agregarPeso( id:String,  nombre:String, Apellido:String){

        if(editTextPregunta1.text.isNotEmpty() && editTextPregunta2.text.isNotEmpty()){
            FirebaseService().initFirestore(id,nombre,Apellido,editTextPregunta1.text.toString().toFloat(),editTextPregunta2.text.toString().toFloat())
            startActivity(Intent(this,ProgressActivity::class.java))
            this.finish()
        }
        else{
            Toast.makeText(this,"No deje campos", Toast.LENGTH_SHORT).show()

        }

    }

}