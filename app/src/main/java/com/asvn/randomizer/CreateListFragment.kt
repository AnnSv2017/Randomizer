package com.asvn.randomizer

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.asvn.randomizer.databinding.FragmentCreateListBinding

class CreateListFragment : Fragment() {
    private var _binding: FragmentCreateListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CreateListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateListBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = AppDatabase.getInstance(application).listDao

        val viewModelFactory = CreateListViewModelFactory(dao)
        viewModel = ViewModelProvider(
            this, viewModelFactory)[CreateListViewModel::class.java]
        binding.createListViewModel = viewModel // Присваиваем значение переменоой связывания данных
        binding.lifecycleOwner = viewLifecycleOwner // Позволяет макету реагировать на обновления данных LiveData

        val adapter = ListItemAdapter { item ->
            showEditDialog(item)
        }
        binding.itemsList.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.save.observe(viewLifecycleOwner, Observer { saved ->
            if (saved) {
                view.findNavController()
                    .navigate(R.id.action_createListFragment_to_mainFragment)
                viewModel.saveCompleted()
            }
        })

        return view
    }

    private fun showEditDialog(item: Item) {
        val context = requireContext()
        val editText = EditText(context).apply {
            setText(item.name)
            setSelection(item.name.length)
        }

        AlertDialog.Builder(context)
            .setTitle("Редактировать элемент")
            .setView(editText)
            .setPositiveButton("Сохранить") { _, _ ->
                val newName = editText.text.toString().trim()
                if (newName.isNotEmpty()) {
                    val updatedList = viewModel.items.value.orEmpty().map {
                        if (it.id == item.id) it.copy(name = newName) else it
                    }
                    viewModel.items.value = updatedList
                }
            }
            .setNegativeButton("Отмена", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}