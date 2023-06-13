package com.cs23_ps276.sahabatlansia

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cs23_ps276.sahabatlansia.api.ApiService
import com.cs23_ps276.sahabatlansia.api.LoginRequest
import com.cs23_ps276.sahabatlansia.api.LoginResponse
import com.cs23_ps276.sahabatlansia.databinding.ActivityLoginBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val apiService: ApiService by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://capstone-project-c23-ps276.et.r.appspot.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client) // Set the OkHttpClient with the logging interceptor
            .build()

        retrofit.create(ApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Set initial hints
        val emailHint = getString(R.string.email_hint)
        val passwordHint = getString(R.string.password_hint)
        binding.tilEmail.hint = emailHint
        binding.tilPassword.hint = passwordHint

        binding.edEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.tilEmail.hint = null
            } else if (binding.edEmail.text.isNullOrEmpty()) {
                binding.tilEmail.hint = emailHint
            }
        }

        binding.edPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.tilPassword.hint = null
            } else if (binding.edPassword.text.isNullOrEmpty()) {
                binding.tilPassword.hint = passwordHint
            }
        }

        binding.edEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    binding.tilEmail.hint = emailHint
                }
            }
        })

        binding.edPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    binding.tilPassword.hint = passwordHint
                }
            }
        })

        binding.loginButton.setOnClickListener {
            if (isEmailValid() && isPasswordValid()) {
                val email = binding.edEmail.text.toString().trim()
                val password = binding.edPassword.text.toString().trim()

                val request = LoginRequest(email, password)
                loginUser(request)
            }
        }

        binding.noAccount.setOnClickListener {
            // Start RegisterActivity
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isEmailValid(): Boolean {
        val email = binding.edEmail.text.toString().trim()

        return if (email.isEmpty()) {
            binding.edEmail.error = "Email is required"
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.edEmail.error = "Invalid email address"
            false
        } else {
            binding.edEmail.error = null
            true
        }
    }

    private fun isPasswordValid(): Boolean {
        val password = binding.edPassword.text.toString().trim()

        return if (password.isEmpty()) {
            binding.edPassword.error = "Password is required"
            false
        } else {
            binding.edPassword.error = null
            true
        }
    }

    private fun loginUser(request: LoginRequest) {
        apiService.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse?.statusCode == 200) {
                        // Handle successful login
                        Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@LoginActivity, HomepageActivity::class.java)
                        startActivity(intent)
                    } else {
                        // Handle login failure with incorrect credentials
                        Toast.makeText(this@LoginActivity, "Invalid email or password", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Handle login failure due to other reasons (e.g., server error)
                    Toast.makeText(this@LoginActivity, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }


            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(
                    this@LoginActivity,
                    "Network Failure: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}

