package com.cs23_ps276.sahabatlansia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cs23_ps276.sahabatlansia.R
import com.cs23_ps276.sahabatlansia.data.Caregiver

class ListCaregiverAdapter(private val listCaregiver: ArrayList<Caregiver>): RecyclerView.Adapter<ListCaregiverAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_caregiver_card)
        val tvName: TextView = itemView.findViewById(R.id.tv_nama_caregiver_card)
        val tvAsal: TextView = itemView.findViewById(R.id.tv_asal_caregiver_card)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.caregiver_card,
            parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listCaregiver.size


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (nama, asal, photo) = listCaregiver[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = nama
        holder.tvAsal.text = asal
    }
}