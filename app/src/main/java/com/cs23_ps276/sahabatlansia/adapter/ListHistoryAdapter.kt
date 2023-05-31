package com.cs23_ps276.sahabatlansia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cs23_ps276.sahabatlansia.R
import com.cs23_ps276.sahabatlansia.data.Caregiver
import com.cs23_ps276.sahabatlansia.data.History

class ListHistoryAdapter(private val listHistory: ArrayList<History>): RecyclerView.Adapter<ListHistoryAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvWaktu: TextView = itemView.findViewById(R.id.tv_waktu_history)
        val tvName: TextView = itemView.findViewById(R.id.tv_nama_history)
        val tvAsal: TextView = itemView.findViewById(R.id.tv_asal_history)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.history_card,
            parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listHistory.size


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (waktu, nama, asal) = listHistory[position]
        holder.tvWaktu.text = waktu
        holder.tvName.text = nama
        holder.tvAsal.text = asal
    }
}