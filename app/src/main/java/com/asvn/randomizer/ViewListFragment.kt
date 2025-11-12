package com.asvn.randomizer

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.asvn.randomizer.databinding.FragmentViewListBinding

class ViewListFragment : Fragment() {
    private var _binding: FragmentViewListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ViewListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_view_list, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_edit_list_name -> {
                        showEditListNameDialog()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewListBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = AppDatabase.getInstance(application).listDao

        val listId = ViewListFragmentArgs.fromBundle(requireArguments()).listId

        val viewModelFactory = ViewListViewModelFactory(listId, dao)
        viewModel = ViewModelProvider(
            this, viewModelFactory)[ViewListViewModel::class.java]
        binding.viewListViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = ListItemAdapter { item ->
            showEditItemNameDialog(item)
        }
        binding.itemsList.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })


        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.listName.observe(viewLifecycleOwner) { name ->
            (requireActivity() as AppCompatActivity).supportActionBar?.title = name
        }
    }

    private fun showEditListNameDialog() {
        val context = requireContext()
        val editText = EditText(context).apply {
            setText(viewModel.listName.value ?: "")
            setSelection(text.length)
        }

        AlertDialog.Builder(context)
            .setTitle("Редактировать название списка")
            .setView(editText)
            .setPositiveButton("Сохранить") { _, _ ->
                val newName = editText.text.toString().trim()
                if (newName.isNotEmpty()) {
                    // Обновляем БД
                    viewModel.updateListName(newName)
                }
            }
            .setNegativeButton("Отмена", null)
            .show()
    }


    private fun showEditItemNameDialog(item: Item) {
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
                    viewModel.updateItemName(item.copy(name = newName))
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