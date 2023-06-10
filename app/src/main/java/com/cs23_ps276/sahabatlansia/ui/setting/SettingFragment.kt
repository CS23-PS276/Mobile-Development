package com.cs23_ps276.sahabatlansia.ui.setting

import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import com.cs23_ps276.sahabatlansia.R
import com.cs23_ps276.sahabatlansia.databinding.FragmentSettingBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        val root: View = binding.root

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

        binding.saveButton.setOnClickListener {
            if (isEmailValid() && isPasswordValid()) {
                // Perform profile update
            }
        }

        return root
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
            binding.tilEmail.error = "Email is required"
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Invalid email address"
            false
        } else {
            binding.tilEmail.error = null
            true
        }
    }

    private fun isPasswordValid(): Boolean {
        val password = binding.edPassword.text.toString().trim()

        return if (password.isEmpty()) {
            binding.tilPassword.error = "Password is required"
            false
        } else if (password.length < 6) {
            binding.tilPassword.error = "Password must be at least 6 characters long"
            false
        } else if (password != binding.edConfirmPassword.text.toString().trim()) {
            binding.tilConfirmPassword.error = "Passwords do not match"
            false
        } else {
            binding.tilPassword.error = null
            binding.tilConfirmPassword.error = null
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}