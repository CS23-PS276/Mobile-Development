package com.cs23_ps276.sahabatlansia.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cs23_ps276.sahabatlansia.R
import com.cs23_ps276.sahabatlansia.databinding.FragmentTaskDetailBinding

import com.cs23_ps276.sahabatlansia.notification.model.Task
import com.cs23_ps276.sahabatlansia.notification.viewmodel.TaskViewModel
import com.cs23_ps276.sahabatlansia.notification.viewmodel.TaskViewModelFactory

class TaskDetailFragment : Fragment() {
    private val navigationArgs: TaskDetailFragmentArgs by navArgs()

    private val viewModel: TaskViewModel by activityViewModels {
        TaskViewModelFactory(
            (activity?.application as BaseApplication).database.taskDao()
        )
    }

    private lateinit var task: Task

    private var _binding: FragmentTaskDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id
        viewModel.retrieveTask(id).observe(this.viewLifecycleOwner) { selectedTask ->
            task = selectedTask
            bindTask()
        }
    }

    private fun bindTask() {
        binding.apply {
            taskTitle.text = task.taskTitle
            taskDescription.text = task.taskDescription
            taskDate.text = task.getFullDateString()

            editTaskFab.setOnClickListener {
                val action = TaskDetailFragmentDirections.actionTaskDetailFragmentToAddTaskDialogFragment(
                    getString(R.string.edit_task_title), task.id
                )
                findNavController().navigate(action)
            }
        }
    }
}
