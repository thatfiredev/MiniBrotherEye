package io.github.rosariopfernandes.minibrothereye.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.rosariopfernandes.minibrothereye.R
import io.github.rosariopfernandes.minibrothereye.databinding.FragmentListBinding
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()

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

        val charactersAdapter = CharacterAdapter { item, extras ->
            val args = bundleOf("id" to item.id)
            findNavController().navigate(R.id.action_ListFragment_to_InfoFragment, args,
                null, extras)
        }.apply { addLoadStateListener { binding.loadState = it.refresh } }

        with (binding) {
            characterAdapter = charactersAdapter
            swipeRefreshLayout.setOnRefreshListener {
                lifecycleScope.launch {
                    viewModel.refreshList()
                }
            }
        }

        viewModel.characters.observe(viewLifecycleOwner, Observer { pagingData ->
            lifecycleScope.launch {
                charactersAdapter.submitData(pagingData)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}