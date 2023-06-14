package com.cs23_ps276.sahabatlansia

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs23_ps276.sahabatlansia.adapter.ArticleAdapter
import com.cs23_ps276.sahabatlansia.api.ApiService
import com.cs23_ps276.sahabatlansia.api.Article
import com.cs23_ps276.sahabatlansia.api.ArticleRequest
import com.cs23_ps276.sahabatlansia.api.ArticleResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArticleActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        apiService = createApiService()

        val searchButton = findViewById<Button>(R.id.searchButton)
        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        articleAdapter = ArticleAdapter(this)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ArticleActivity)
            adapter = articleAdapter
        }

        searchButton.setOnClickListener {
            val articleTitle = searchEditText.text.toString()
            if (articleTitle.isNotEmpty()) {
                searchArticles(articleTitle)
            } else {
                Toast.makeText(this@ArticleActivity, "Please enter an article title", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createApiService(): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("API_LOG", message) // Log the message to Logcat
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY // Set the desired log level
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://function-article-jo6twt65na-et.a.run.app")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }


    private fun searchArticles(articleTitle: String) {
        val request = ArticleRequest(articleTitle)

        apiService.getRecommendedArticles(request).enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(call: Call<ArticleResponse>, response: Response<ArticleResponse>) {
                if (response.isSuccessful) {
                    val articleIds = response.body()?.result
                    if (articleIds != null) {
                        // Update the RecyclerView with the recommended article list
                        updateRecommendedArticles(articleIds)
                    }
                } else {
                    Toast.makeText(this@ArticleActivity, "Failed to get recommended articles", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                Toast.makeText(this@ArticleActivity, "Failed to communicate with the server", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateRecommendedArticles(articles: List<Article>) {
        articleAdapter.setArticles(articles)
    }

    private fun getArticleTitles(articles: List<Article>): List<String> {
        return articles.map { it.judul }
    }

}