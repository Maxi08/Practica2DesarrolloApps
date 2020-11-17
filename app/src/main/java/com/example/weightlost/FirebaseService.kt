package com.example.weightlost
import android.util.Log
import android.widget.Toast
import com.example.weightlost.clases.Pesos
import com.example.weightlost.clases.Usuarios
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import java.util.*

class FirebaseService {

    companion object {
        private val TAG = "DocSnippets"
    }

    val db = FirebaseFirestore.getInstance();

    val firestoreSettings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true).build();

    init {
        db.firestoreSettings = firestoreSettings
    }

    public fun initFirestore(id: String, name: String, last: String, pesoA: Float, pesoO: Float) {
        val usuario = Usuarios( name, last, pesoA, pesoO)
        db.collection("usuarios").document(id).set(usuario)
    }

    public fun initPesoFirestore(idU: String, fecha: Date, peso: Float) {
        val pesoUsuario = Pesos(idU, fecha, peso)
        db.collection("pesos").add(pesoUsuario)
    }

    fun getUserById(id: String, response: IResult<Usuarios>) {
        db.collection("usuarios").document(id).get()
            .addOnSuccessListener { document ->
                run {
                    val user = document.toObject(Usuarios::class.java)
                    response.onSuccess(user)
                }
            }.addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }
    fun getPesosById(id:String,res:IResult<List<Pesos>>){

        db.collection("pesos")
            .whereEqualTo("idU", id)
            .get()
            .addOnSuccessListener { documents ->
                val lista= mutableListOf<Pesos>()
                for (document in documents) {
                    val peso=document.toObject(Pesos::class.java)
                    lista.add(peso)


                }
                res.onSuccess(lista)
            }
            .addOnFailureListener { exception ->
                res.onError(exception)
            }

    }



    interface IResult<T> {
        fun onSuccess(items: T?)
        fun onError(exception: Exception)
    }
}

