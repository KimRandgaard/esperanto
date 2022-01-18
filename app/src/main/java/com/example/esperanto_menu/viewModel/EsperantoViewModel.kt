package com.example.esperanto_menu.viewModel

import android.content.Context
import android.media.MediaMetadataRetriever
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.esperanto_menu.model.data.Channel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream

class EsperantoViewModel: ViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text
    var isMusicServiceBound: Boolean = false

    private fun loadJson(context: Context): String {
        var input: InputStream? = null
        var jsonString: String

        input = context.assets.open("radio.json")

        val size = input.available()

        // Laver en buffer med størrelsen
        val buffer = ByteArray(size)

        // Læser data fra InputStream ind til Bufferen
        input.read(buffer)

        // Laver en Json string
        jsonString = String(buffer)
        input?.close()
        return jsonString;
    }


    fun getchannellist(context: Context): List<Channel> {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val type = Types.newParameterizedType(List::class.java, Channel::class.java)
        val jsonAdapter : JsonAdapter<List<Channel>> = moshi.adapter(type)

        val input = context.assets.open("radio.json")

        val source = loadJson(context)
        val result: List<Channel> = jsonAdapter.fromJson(source)!!

        return result

    }

    fun getEpisode(episode: String, context: Context): Channel{
        val channels = getchannellist(context)

        return channels.filter {

            it.plennomo == episode
        }.first()

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

    fun getEpisodesByChannel(channel: String, context: Context): List<Channel>{
        val channels = getchannellist(context)
        return channels.filter {
            it.nomo == channel
        }

    }

}