package com.cs23_ps276.sahabatlansia

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cs23_ps276.sahabatlansia.api.ApiService
import com.cs23_ps276.sahabatlansia.api.RegisterRequest
import com.cs23_ps276.sahabatlansia.api.RegisterResponse
import com.cs23_ps276.sahabatlansia.databinding.ActivityRegisterBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
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
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupCustomEditText(
            binding.edUsername,
            binding.tilUsername,
            binding.tilEmail,
            getString(R.string.username_hint)
        )
        setupCustomEditText(
            binding.edEmail,
            binding.tilEmail,
            binding.tilPassword,
            getString(R.string.email_hint)
        )
        setupCustomEditText(
            binding.edPassword,
            binding.tilPassword,
            binding.tilConfirmPassword,
            getString(R.string.password_hint)
        )
        setupCustomEditText(
            binding.edConfirmPassword,
            binding.tilConfirmPassword,
            null,
            getString(R.string.confirm_password_hint)
        )

        binding.registerButton.setOnClickListener {
            if (isUsernameValid() && isEmailValid() && isPasswordValid()) {
                val username = binding.edUsername.text.toString().trim()
                val email = binding.edEmail.text.toString().trim()
                val password = binding.edPassword.text.toString().trim()
                val retypePassword = binding.edConfirmPassword.text.toString().trim()

                val request = RegisterRequest(username, email, password, retypePassword)
                registerUser(request)
            }
        }

        binding.haveAccount.setOnClickListener {
            // Start LoginActivity
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerUser(request: RegisterRequest) {
        apiService.register(request).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    Toast.makeText(
                        this@RegisterActivity,
                        "Registration Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    val errorResponse = response.errorBody()?.string()
                    val errorMessage = try {
                        val error = Gson().fromJson(errorResponse, RegisterResponse::class.java)
                        error.message
                    } catch (e: Exception) {
                        "Registration Failed: $errorResponse"
                    }
                    Toast.makeText(
                        this@RegisterActivity,
                        errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Network Failure: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    private fun setupCustomEditText(
        editText: TextInputEditText,
        inputLayout: TextInputLayout,
        nextInputLayout: TextInputLayout?,
        hint: String
    ) {
        editText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                inputLayout.hint = null
            } else if (editText.text.isNullOrEmpty()) {
                inputLayout.hint = hint
            }
        }

        editText.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_NEXT || (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER)) {
                nextInputLayout?.let {
                    it.editText?.requestFocus()
                    it.hint = null
                }
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
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
        } else if (password.length < 6) {
            binding.edPassword.error = "Password must be at least 6 characters long"
            false
        } else if (password != binding.edConfirmPassword.text.toString().trim()) {
            binding.edConfirmPassword.error = "Passwords do not match"
            false
        } else {
            binding.edPassword.error = null
            binding.edConfirmPassword.error = null
            true
        }
    }

    private fun isUsernameValid(): Boolean {
        val username = binding.edUsername.text.toString().trim()

        return if (username.length < 5) {
            binding.edUsername.error = "Username must be at least 5 characters long"
            false
        } else {
            binding.edUsername.error = null
            true
        }
    }
}