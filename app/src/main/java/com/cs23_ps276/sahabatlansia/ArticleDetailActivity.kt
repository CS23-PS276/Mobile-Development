package com.cs23_ps276.sahabatlansia

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ArticleDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        val articleTitle = intent.getStringExtra("articleTitle")

        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        titleTextView.text = articleTitle
    }
}

