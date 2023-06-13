package com.cs23_ps276.sahabatlansia.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cs23_ps276.sahabatlansia.ArticleActivity
import com.cs23_ps276.sahabatlansia.CariCaregiverActivity
import com.cs23_ps276.sahabatlansia.R
import com.cs23_ps276.sahabatlansia.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        binding.appTitle.text = getString(R.string.app_name)
        binding.appMotto.text = getString(R.string.app_motto)
        binding.appVersion.text = getString(R.string.app_version_1_0)

        // Set up click listeners for the buttons
        binding.caregiverButton.setOnClickListener {
            val intent = Intent(activity, CariCaregiverActivity::class.java)
            startActivity(intent)
        }

        binding.articleButton.setOnClickListener {
            val intent = Intent(activity, ArticleActivity::class.java)
            startActivity(intent)
        }

        binding.notificationButton.setOnClickListener {
            //val intent = Intent(activity, NotifikasiActivity::class.java)
            //startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}