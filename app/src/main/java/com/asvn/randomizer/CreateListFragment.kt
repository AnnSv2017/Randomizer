package com.asvn.randomizer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.asvn.randomizer.databinding.FragmentCreateListBinding

class CreateListFragment : Fragment() {
    private var _binding: FragmentCreateListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateListBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = AppDatabase.getInstance(application).itemDao
        val viewModelFactory = CreateListViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory)[CreateListViewModel::class.java]

        binding.createListViewModel = viewModel // Присваиваем значение переменоой связывания данных
        binding.lifecycleOwner = viewLifecycleOwner // Позволяет макету реагировать на обновления данных LiveData


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}