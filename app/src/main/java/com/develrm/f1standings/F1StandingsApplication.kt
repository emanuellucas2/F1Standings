package com.develrm.f1standings

import android.app.Application
import com.google.firebase.FirebaseApp

class F1StandingsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}