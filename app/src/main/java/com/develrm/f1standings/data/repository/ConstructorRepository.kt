package com.develrm.f1standings.data.repository

import com.develrm.f1standings.data.model.ConstructorModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ConstructorRepository private constructor() {

    companion object {
        @Volatile
        private var instance: ConstructorRepository? = null

        fun getInstance(): ConstructorRepository {
            return instance ?: synchronized(this) {
                instance ?: ConstructorRepository().also { instance = it }
            }
        }
    }

        fun getMyModels(callback: (List<ConstructorModel>) -> Unit) {
            val myModelsRef = FirebaseDatabase.getInstance().reference.child("constructors")

            myModelsRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val myModelsList = mutableListOf<ConstructorModel>()
                    for (childSnapshot in snapshot.children) {
                        val myModel = childSnapshot.getValue(ConstructorModel::class.java)
                        myModel?.let { myModelsList.add(it) }
                    }
                    callback(myModelsList)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle onCancelled event if needed
                }
            })
        }
}