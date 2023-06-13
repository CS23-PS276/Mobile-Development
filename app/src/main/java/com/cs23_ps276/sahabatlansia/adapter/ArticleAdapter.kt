package com.cs23_ps276.sahabatlansia.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cs23_ps276.sahabatlansia.ArticleDetailActivity
import com.cs23_ps276.sahabatlansia.R

// ArticleAdapter.kt
class ArticleAdapter(private val context: Context) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    private val articleList: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val articleTitle = articleList[position]
        holder.bind(articleTitle)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ArticleDetailActivity::class.java)
            intent.putExtra("articleTitle", articleTitle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    fun setArticles(articles: List<String>) {
        articleList.clear()
        articleList.addAll(articles)
        notifyDataSetChanged()
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)

        fun bind(articleTitle: String) {
            titleTextView.text = articleTitle
        }
    }
}

