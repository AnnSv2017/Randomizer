package com.asvn.randomizer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.asvn.randomizer.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: MainListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = AppDatabase.getInstance(application).listDao

        val viewModelFactory = MainListViewModelFactory(dao)
        viewModel = ViewModelProvider(
            this, viewModelFactory)[MainListViewModel::class.java]
        binding.mainFViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = MainListAdapter { listId ->
            val action = MainFragmentDirections
                .actionMainFragmentToViewListFragment(listId)
            view.findNavController().navigate(action)
        }
        binding.mainList.adapter = adapter

        viewModel.listElements.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.createFab.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_mainFragment_to_createListFragment)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}