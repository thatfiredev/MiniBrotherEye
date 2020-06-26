package io.github.rosariopfernandes.minibrothereye.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.github.rosariopfernandes.minibrothereye.R
import io.github.rosariopfernandes.minibrothereye.databinding.FragmentListBinding
import io.github.rosariopfernandes.minibrothereye.util.DataResult

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListFragment : Fragment() {

    private val viewModel by viewModels<ListViewModel> { ListViewModelFactory() }

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val charactersAdapter = CharacterAdapter { item ->
            Toast.makeText(context, "${item.name} doesnt have a profile yet",
                Toast.LENGTH_SHORT).show()
        }

        with (binding) {
            rvCharacters.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            rvCharacters.adapter = charactersAdapter
        }

        viewModel.charactersLiveData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is DataResult.Success -> {
                    val characters = result.data
                    val items = characters.map { character ->
                        return@map CharacterListItem(
                            id = character.id,
                            name = character.name,
                            photoUrl = character.images.md
                        )
                    }
                    charactersAdapter.submitList(items)
                    binding.tvError.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    binding.rvCharacters.visibility = View.VISIBLE
                }
                is DataResult.InProgress -> {
                    binding.tvError.visibility = View.GONE
                    binding.rvCharacters.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                is DataResult.Error -> {
                    val error = result.exception.message
                    error?.let {
                        binding.tvError.text = getString(R.string.error_cant_load_data)
                    }
                    binding.progressBar.visibility = View.GONE
                    binding.rvCharacters.visibility = View.GONE
                    binding.tvError.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}