package com.example.myapplication


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class adapter(private val myDataset: Array<String>) :
    RecyclerView.Adapter<adapter.MyViewHolder>() {

    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var textViewreview: TextView? = null
        var communityphoto: ImageView? = null
        init {
            textViewreview = v.findViewById(R.id.textViewreview)
            communityphoto = v.findViewById(R.id.communityphoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter.MyViewHolder {

        val linearLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_post, parent, false) as TextView
        return MyViewHolder(linearLayout)
    }

    override fun getItemCount() = myDataset.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    }
}