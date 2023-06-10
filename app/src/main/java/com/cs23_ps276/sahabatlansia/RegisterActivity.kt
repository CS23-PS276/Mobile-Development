package com.cs23_ps276.sahabatlansia

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.cs23_ps276.sahabatlansia.databinding.ActivityRegisterBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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
            if (isEmailValid() && isPasswordValid()) {
                // Start LoginActivity
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        binding.haveAccount.setOnClickListener {
            // Start LoginActivity
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
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
}