package com.udacity.shoestore.ui.login

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding
import com.udacity.shoestore.ui.base.BaseFragment
import com.udacity.shoestore.utils.Event
import com.udacity.shoestore.utils.EventObserver

class LoginFragment : BaseFragment() {

  private var _binding: LoginFragmentBinding? = null
  private val binding: LoginFragmentBinding
    get() = _binding!!

  private val viewModel by viewModels<LoginViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    _binding = LoginFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    initViewModel()
    observeValidationErrors()
    observeEvents()
  }

  private fun initViewModel() {
    binding.lifecycleOwner = viewLifecycleOwner
    binding.viewModel = viewModel
  }

  private fun observeValidationErrors() {
    observeValidationError(viewModel.errorEmail, binding.emailTextInputLayout)
    observeValidationError(viewModel.errorPassword, binding.passwordTextInputLayout)
  }

  private fun observeEvents() {
    viewModel.eventNavigateNext.observe(viewLifecycleOwner, EventObserver { navigate ->
      if (navigate) {
        navigateToNextScreen()
      }
    })
  }

  private fun navigateToNextScreen() {
    findNavController().navigate(
      LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
    )
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    menu.findItem(R.id.action_logout)?.let {
      it.isVisible = false
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
