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
    lateinit var viewModel: CreateListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateListBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this)[CreateListViewModel::class.java]
        binding.createListViewModel = viewModel // Присваиваем значение переменоой связывания данных
        binding.lifecycleOwner = viewLifecycleOwner // Позволяет макету реагировать на обновления данных LiveData

//        // Observe нужен только когда у нас нет dataBinding, а есть только viewBinding -> макет и viewModel не связаны напрямую
//        viewModel.text.observe(viewLifecycleOwner, Observer {newValue ->
//            binding.textView.text = viewModel.text.toString()
//        })

        binding.addBtn.setOnClickListener {
            val text = binding.editText.text.toString()
            binding.textView.text = text
//            binding.textView.text = viewModel.text
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}