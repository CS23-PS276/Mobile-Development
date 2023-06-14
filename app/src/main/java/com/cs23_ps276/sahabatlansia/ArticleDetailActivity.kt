package com.cs23_ps276.sahabatlansia

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ArticleDetailActivity : AppCompatActivity() {
    private lateinit var articleTitle: String
    private lateinit var articleContent: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        // Retrieve the article details from the intent
        articleTitle = intent.getStringExtra("articleTitle") ?: ""
        articleContent = intent.getStringExtra("articleContent") ?: ""

        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val contentTextView = findViewById<TextView>(R.id.contentTextView)
        val buttonContainer = findViewById<LinearLayout>(R.id.buttonContainer)

        titleTextView.text = articleTitle
        contentTextView.text = articleContent

        addLikeButton(buttonContainer)
        addShareButton(buttonContainer)
    }

    private fun addLikeButton(container: LinearLayout) {
        val likeButton = ImageView(this)
        likeButton.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        likeButton.setImageResource(R.drawable.like_logo) // Replace "like_logo" with your actual like logo resource
        likeButton.setOnClickListener {
            // Perform the like action
            // Replace the code below with your actual like implementation
            Toast.makeText(this, "Liked!", Toast.LENGTH_SHORT).show()
        }
        container.addView(likeButton)
    }

    private fun addShareButton(container: LinearLayout) {
        val shareButton = ImageView(this)
        shareButton.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        shareButton.setImageResource(R.drawable.share_logo) // Replace "share_logo" with your actual share logo resource
        shareButton.setOnClickListener {
            // Create a share intent with the article details
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, articleTitle)
            shareIntent.putExtra(Intent.EXTRA_TEXT, getArticleShareText())

            // Start the share activity
            startActivity(Intent.createChooser(shareIntent, "Share Article"))
        }
        container.addView(shareButton)
    }

    private fun getArticleShareText(): String {
        // Format the article details as a shareable text
        val shareText = "Check out this article:\n\n" +
                "Title: $articleTitle\n" +
                "Content: $articleContent\n\n" +
                "Shared from My App"

        return shareText
    }
}



