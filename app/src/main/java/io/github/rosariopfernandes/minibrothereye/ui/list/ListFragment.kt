package io.github.rosariopfernandes.minibrothereye.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.github.rosariopfernandes.minibrothereye.databinding.FragmentListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListFragment : Fragment() {

    private val viewModel by viewModels<ListViewModel> {
        ListViewModelFactory(requireActivity().applicationContext)
    }

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

        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                charactersAdapter.submitData(pagingData)
            }
        }

        charactersAdapter.addLoadStateListener { loadState ->
            with (binding) {
                when (loadState.refresh) {
                    is LoadState.Loading -> {
                        progressBar.isVisible = true
                        rvCharacters.isGone = true
                        tvError.isGone = true
                    }
                    is LoadState.Error -> {
                        tvError.text = (loadState.refresh as LoadState.Error).error.message
                        rvCharacters.isGone = true
                        progressBar.isGone = true
                        tvError.isVisible = true
                    }
                    else -> {
                        rvCharacters.isVisible = true
                        progressBar.isGone = true
                        tvError.isGone = true
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}