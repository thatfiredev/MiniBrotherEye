package io.github.rosariopfernandes.minibrothereye.ui.characterinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialContainerTransform
import dagger.hilt.android.AndroidEntryPoint
import io.github.rosariopfernandes.minibrothereye.R
import io.github.rosariopfernandes.minibrothereye.databinding.FragmentCharacterInfoBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class CharacterInfoFragment : Fragment() {

    private val viewModel: CharacterInfoViewModel by viewModels()

    private var _binding: FragmentCharacterInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            fadeMode = MaterialContainerTransform.FADE_MODE_THROUGH
            scrimColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterInfoBinding.inflate(inflater, container, false)
        val args = requireArguments()
        ViewCompat.setTransitionName(binding.root, "${args.getInt("id")}")
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
        viewModel.exception.observe(viewLifecycleOwner, Observer { exception ->
            exception?.let {
                it.printStackTrace()
                Snackbar.make(view, R.string.error_cant_load_data, Snackbar.LENGTH_INDEFINITE)
                    .show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}