package com.udacity.shoestore.ui.shoedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import com.udacity.shoestore.ui.base.BaseFragment
import com.udacity.shoestore.utils.EventObserver
import com.udacity.shoestore.utils.setHomeAsCloseIndicator

class ShoeDetailFragment : BaseFragment() {

  private var _binding: ShoeDetailFragmentBinding? = null
  private val binding: ShoeDetailFragmentBinding
    get() = _binding!!

  private val viewModel by viewModels<ShoeDetailViewModel>()

  private val mainViewModel by activityViewModels<MainViewModel>()

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    _binding = ShoeDetailFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setHomeAsCloseIndicator()
    initViewModel()
    observeValidationErrors()
    observeEvents()
  }

  private fun initViewModel() {
    binding.viewModel = viewModel
    binding.lifecycleOwner = viewLifecycleOwner
  }

  private fun observeValidationErrors() {
    observeValidationError(viewModel.errorName, binding.shoeNameTextInputLayout)
    observeValidationError(viewModel.errorSize, binding.shoeSizeTextInputLayout)
    observeValidationError(viewModel.errorCompany, binding.shoeCompanyNameTextInputLayout)
    observeValidationError(viewModel.errorDescription, binding.shoeDescriptionTextInputLayout)
  }

  private fun observeEvents() {
    viewModel.eventSaveShoe.observe(
        viewLifecycleOwner,
        EventObserver { shoe ->
          mainViewModel.addShoe(shoe)
          navigateBack()
        })
    viewModel.eventNavigateBack.observe(
        viewLifecycleOwner,
        EventObserver { navigate ->
          if (navigate) {
            navigateBack()
          }
        })
  }

  private fun navigateBack() {
    findNavController().popBackStack()
  }
}
