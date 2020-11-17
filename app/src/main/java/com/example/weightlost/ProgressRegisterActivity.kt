package com.example.weightlost

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_progress_register.*
import java.util.*

class ProgressRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_register)


        val datePicker = findViewById<DatePicker>(R.id.date_Picker)

        GuardarActualizacion.setOnClickListener(){
            if(editTextPeso.text.isNotEmpty()){
                saveProgress()
            }
            else{
                Toast.makeText(this,"No deje campos vacios", Toast.LENGTH_LONG).show()
            }

        }

    }
            fun saveProgress(){
                val datePicker=date_Picker
                val date=Date(datePicker.year-1900,datePicker.month,datePicker.dayOfMonth)
                val peso=editTextPeso.text.toString().toFloat()

                FirebaseService().initPesoFirestore(FirebaseAuth.getInstance().currentUser!!.uid,date,peso)
                val intent = Intent(this, ProgressActivity::class.java)
                startActivity(intent)
                this.finish()


            }
    }



