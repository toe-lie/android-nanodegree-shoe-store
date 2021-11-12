package com.udacity.shoestore.ui.onboarding.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.WelcomeFragmentBinding
import com.udacity.shoestore.utils.EventObserver

class WelcomeFragment : Fragment() {

  private var _binding: WelcomeFragmentBinding? = null
  private val binding: WelcomeFragmentBinding
    get() = _binding!!

  private val viewModel by viewModels<WelcomeViewModel>()

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    _binding = WelcomeFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    initViewModel()
    observeEvents()
  }

  private fun initViewModel() {
    binding.viewModel = viewModel
    binding.lifecycleOwner = viewLifecycleOwner
  }

  private fun observeEvents() {
    viewModel.eventNavigateNext.observe(
        viewLifecycleOwner,
        EventObserver { navigate ->
          if (navigate) {
            navigateToNextScreen()
          }
        })
  }

  private fun navigateToNextScreen() {
    findNavController()
        .navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment())
  }
}
