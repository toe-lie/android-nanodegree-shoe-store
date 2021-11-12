package com.udacity.shoestore.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.ui.INPUT_VALIDATION_NO_ERROR
import com.udacity.shoestore.utils.Event

class LoginViewModel : ViewModel() {

  val email = MutableLiveData<String>()
  val password = MutableLiveData<String>()

  private val _errorEmail = MutableLiveData<Event<Int>>()
  val errorEmail: LiveData<Event<Int>>
    get() = _errorEmail

  private val _errorPassword = MutableLiveData<Event<Int>>()
  val errorPassword: LiveData<Event<Int>>
    get() = _errorPassword

  private val _eventNavigateNext = MutableLiveData<Event<Boolean>>()
  val eventNavigateNext: LiveData<Event<Boolean>>
    get() = _eventNavigateNext

  init {
    email.value = ""
    password.value = ""
  }

  fun onLoginClicked() {
    _eventNavigateNext.value = Event(true)
  }

  fun onCreateAccountClicked() {
    if (validateInputs()) {
      _eventNavigateNext.value = Event(true)
    }
  }

  private fun validateInputs(): Boolean {
    var isAllInputValid = true

    val emailValidationResult = validateEmail()
    if (!emailValidationResult) {
      isAllInputValid = false
    }

    val passwordValidationResult = validatePassword()
    if (!passwordValidationResult) {
      isAllInputValid = false
    }

    return isAllInputValid
  }

  private fun validateEmail(): Boolean {
    return if (email.value.isNullOrEmpty()) {
      _errorEmail.value = Event(R.string.error_email_required)
      false
    } else {
      _errorEmail.value = Event(INPUT_VALIDATION_NO_ERROR)
      true
    }
  }

  private fun validatePassword(): Boolean {
    return if (password.value.isNullOrEmpty()) {
      _errorPassword.value = Event(R.string.error_password_required)
      false
    } else {
      _errorPassword.value = Event(INPUT_VALIDATION_NO_ERROR)
      true
    }
  }
}
