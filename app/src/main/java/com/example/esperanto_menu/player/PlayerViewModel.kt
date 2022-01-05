package com.example.esperanto_menu.player

import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.lifecycle.ViewModel

class PlayerViewModel : ViewModel() {
    private val binder by lazy {MusicBinder()}

    //override fun onBind(intent: Intent?): IBinder= binder

    inner class MusicBinder : Binder(){
        fun getService(): PlayerViewModel= this@PlayerViewModel
    }


}