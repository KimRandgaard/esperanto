package com.example.esperanto_menu.ui.episodes

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.esperanto_menu.model.data.Channel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream

class EpisodesViewModel: ViewModel() {

    private val _episodes = MutableLiveData<String>()
    val episodes: LiveData<String> = _episodes

    private fun loadJson(context: Context): String{
        var input: InputStream? = null
        var jsonString: String

        input = context.assets.open("radio.json")

        val size = input.available()

        val buffer = ByteArray(size)

        input.read(buffer)

        jsonString = String(buffer)
        input?.close()
        return jsonString
    }

    fun getEpisodeList(context: Context): List<Channel> {
        val episodeMoshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val episodeType = Types.newParameterizedType(List::class.java,Channel::class.java)
        val epijsonAdapter : JsonAdapter<List<Channel>> = episodeMoshi.adapter(episodeType)

        val epiInput = context.assets.open("radio.json")

        val episource = loadJson(context)
        val epiresult: List<Channel> = epijsonAdapter.fromJson(episource)!!

        return epiresult
    }
}

























