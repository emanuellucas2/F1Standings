package com.develrm.f1standings.data.repository

import com.develrm.f1standings.data.model.DriverModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DriverRepository private constructor() {

    companion object {
        @Volatile
        private var instance: DriverRepository? = null

        fun getInstance(): DriverRepository {
            return instance ?: synchronized(this) {
                instance ?: DriverRepository().also { instance = it }
            }
        }
    }

    fun getMyModels(callback: (List<DriverModel>) -> Unit) {
        val myModelsRef = FirebaseDatabase.getInstance().reference.child("drivers")

        myModelsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val myModelsList = mutableListOf<DriverModel>()
                for (childSnapshot in snapshot.children) {
                    val myModel = childSnapshot.getValue(DriverModel::class.java)
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