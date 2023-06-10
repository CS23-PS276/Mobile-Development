package com.cs23_ps276.sahabatlansia

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.cs23_ps276.sahabatlansia.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

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
                // Start OnBoardingActivity
                val intent = Intent(this@LoginActivity, HomepageActivity::class.java)
                startActivity(intent)
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
}

