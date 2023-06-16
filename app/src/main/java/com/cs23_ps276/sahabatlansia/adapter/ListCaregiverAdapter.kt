package com.cs23_ps276.sahabatlansia.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cs23_ps276.sahabatlansia.DetailCaregiverActivity
import com.cs23_ps276.sahabatlansia.R

class ListCaregiverAdapter(private val context: Context) :
    RecyclerView.Adapter<ListCaregiverAdapter.ViewHolder>() {

    private var caregiverList: List<String> = listOf()
    private lateinit var onItemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(caregiver: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
    fun setCaregiver(caregivers: List<String>) {
        caregiverList = caregivers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_caregiver, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val caregiver = caregiverList[position]
        holder.bind(caregiver)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailCaregiverActivity::class.java)
            intent.putExtra("caregiverName", caregiver)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return caregiverList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvCaregiverName: TextView = itemView.findViewById(R.id.tv_caregiver_name)
        private val tvCaregiverCity: TextView = itemView.findViewById(R.id.tv_caregiver_city)

        fun bind(caregiver: String) {
            val parts = caregiver.split(" - ")
            if (parts.size == 2) {
                val name = parts[1]
                val city = parts[0]
                tvCaregiverName.text = name
                tvCaregiverCity.text = city
            }
        }
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val caregiver = caregiverList[position]
                    onItemClickListener.onItemClick(caregiver)
                }
            }
        }
    }
}