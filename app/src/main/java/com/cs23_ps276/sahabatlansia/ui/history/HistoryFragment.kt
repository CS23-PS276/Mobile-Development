package com.cs23_ps276.sahabatlansia.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cs23_ps276.sahabatlansia.R
import com.cs23_ps276.sahabatlansia.adapter.ListHistoryAdapter
import com.cs23_ps276.sahabatlansia.data.History
import com.cs23_ps276.sahabatlansia.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val list = ArrayList<History>()

    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvHistory.setHasFixedSize(true)
        list.addAll(getListHistory())
        showRecyclerList()

        return root
    }

    private fun getListHistory(): ArrayList<History> {
        val dataName = resources.getStringArray(R.array.data_nama)
        val dataAsal = resources.getStringArray(R.array.data_asal)
        val dataWaktu = resources.getStringArray(R.array.data_waktu)
        val listHistory = ArrayList<History>()
        for (i in dataName.indices) {
            val history = History(dataWaktu[i], dataName[i], dataAsal[i])
            listHistory.add(history)
        }
        return listHistory
    }

    private fun showRecyclerList() {
        binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
        val listHistoryAdapter = ListHistoryAdapter(list)
        binding.rvHistory.adapter = listHistoryAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
