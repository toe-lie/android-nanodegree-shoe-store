package com.udacity.shoestore.ui.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.google.android.material.textfield.TextInputLayout
import com.udacity.shoestore.ui.INPUT_VALIDATION_NO_ERROR
import com.udacity.shoestore.ui.login.LoginViewModel
import com.udacity.shoestore.utils.Event
import com.udacity.shoestore.utils.EventObserver

abstract class BaseFragment : Fragment() {

  protected fun observeValidationError(
      errorLiveData: LiveData<Event<Int>>,
      textInputLayout: TextInputLayout
  ) {
    errorLiveData.observe(
        viewLifecycleOwner,
        EventObserver { errorResId ->
          if (errorResId == INPUT_VALIDATION_NO_ERROR) {
            textInputLayout.error = null
            textInputLayout.isErrorEnabled = false
          } else {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = requireContext().getString(errorResId)
          }
        })
  }
}
