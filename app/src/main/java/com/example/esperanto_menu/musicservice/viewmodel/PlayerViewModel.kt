package com.example.esperanto_menu.musicservice.viewmodel

import androidx.lifecycle.ViewModel
// Til musicservice er der hentet inspiration fra Ians lektion 3_2 omhandlende MusicService
// fra 3-ugers perioden. Denne omfatter hele musicService package herunder:
// MusicState, PlayerFragment, MusicService samt PlayerViewModel
// : https://learn.inside.dtu.dk/d2l/le/content/80550/viewContent/367175/View
class PlayerViewModel : ViewModel() {
    var isMusicServiceBound: Boolean = false
}