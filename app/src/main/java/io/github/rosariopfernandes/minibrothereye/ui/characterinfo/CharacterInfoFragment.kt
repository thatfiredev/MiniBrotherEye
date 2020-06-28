package io.github.rosariopfernandes.minibrothereye.ui.characterinfo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import coil.api.load
import dagger.hilt.android.AndroidEntryPoint
import io.github.rosariopfernandes.minibrothereye.R
import io.github.rosariopfernandes.minibrothereye.databinding.FragmentCharacterInfoBinding
import io.github.rosariopfernandes.minibrothereye.util.DataResult

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
            .observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is DataResult.Success -> {
                        val characterInfo = result.data
                        Log.e("Character", "${characterInfo.powerstats}")
                        binding.ivCharacterPhoto.load(characterInfo.images.md) {
                            crossfade(true)
                            placeholder(R.drawable.no_portrait)
                        }
                        binding.character = characterInfo
                        binding.rvPowerStats.adapter = PowerStatAdapter(characterInfo.powerstats)
                    }
                    is DataResult.InProgress -> {
                        // TODO: Add Progress bar
                    }
                    is DataResult.Error -> {
                        val error = result.exception
                        error.printStackTrace()
                    }
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}