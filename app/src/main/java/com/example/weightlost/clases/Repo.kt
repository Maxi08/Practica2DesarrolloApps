package com.example.weightlost.clases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class Repo {

    fun getPesosData(): LiveData<MutableList<Pesos>> {
        val mutableData = MutableLiveData<MutableList<Pesos>>()
        val id = FirebaseAuth.getInstance().currentUser!!.uid
        FirebaseFirestore.getInstance().collection("pesos")
            .whereEqualTo("idU", id).get()
            .addOnSuccessListener { result ->
                val listData = mutableListOf<Pesos>()
                for (document in result) {
                   // val objeto=document.toObject<Pesos>()

                    val fecha = document.getString("fecha")

                    val peso = document.getString("peso")

                    var sdf = SimpleDateFormat("yyyy-MM-dd");
                    var strDate = fecha
                    var date = Date(sdf.parse(strDate).getTime());

                    val pesosClase = Pesos(id, date, peso?.toFloat())
                    listData.add(pesosClase)
                }
                mutableData.value=listData
            }
        return mutableData
    }
}
