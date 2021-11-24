package com.example.esperanto_menu.ui.channel

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.esperanto_menu.R


class Channel_Adapter(private val context : Activity, private val arrayList : ArrayList<Channels_Data>)
                        : ArrayAdapter<Channels_Data>(context, R.layout.list_item, arrayList)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.list_item, null)

        val imageView : ImageView = view.findViewById(R.id.ChannelPicture)
        val channelName : TextView = view.findViewById(R.id.tvChannelName)
        val channeldescription : TextView = view.findViewById(R.id.tvChannelDesription)
        val done : TextView = view.findViewById(R.id.Hoert)

        imageView.setImageResource(arrayList[position].imageId)
        channelName.text = arrayList[position].channelName
        channeldescription.text = arrayList[position].channelDescriptor
        done.text = arrayList[position].Hoert

        return view
    }


}