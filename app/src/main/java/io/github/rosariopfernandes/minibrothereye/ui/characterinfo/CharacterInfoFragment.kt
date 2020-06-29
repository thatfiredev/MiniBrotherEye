package io.github.rosariopfernandes.minibrothereye.ui.characterinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.rosariopfernandes.minibrothereye.databinding.FragmentCharacterInfoBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class CharacterInfoFragment : Fragment() {

    private val viewModel: CharacterInfoViewModel by viewModels()

    private var _binding: FragmentCharacterInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterId = requireArguments().getInt("id")
        viewModel.fetchCharacterInfo(characterId)

        with (binding) {
            lifecycleOwner = viewLifecycleOwner
            this.viewmodel = viewModel
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}