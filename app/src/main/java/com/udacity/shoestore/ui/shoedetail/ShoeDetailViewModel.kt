package com.udacity.shoestore.ui.shoedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.ui.INPUT_VALIDATION_NO_ERROR
import com.udacity.shoestore.utils.Event

class ShoeDetailViewModel : ViewModel() {

  val name = MutableLiveData<String>()
  val sizeString = MutableLiveData<String>()
  val company = MutableLiveData<String>()
  val description = MutableLiveData<String>()

  private val _errorName = MutableLiveData<Event<Int>>()
  val errorName: LiveData<Event<Int>>
    get() = _errorName

  private val _errorSize = MutableLiveData<Event<Int>>()
  val errorSize: LiveData<Event<Int>>
    get() = _errorSize

  private val _errorCompany = MutableLiveData<Event<Int>>()
  val errorCompany: LiveData<Event<Int>>
    get() = _errorCompany

  private val _errorDescription = MutableLiveData<Event<Int>>()
  val errorDescription: LiveData<Event<Int>>
    get() = _errorDescription

  private val _eventSaveShoe = MutableLiveData<Event<Shoe>>()
  val eventSaveShoe: LiveData<Event<Shoe>>
    get() = _eventSaveShoe

  private val _eventNavigateBack = MutableLiveData<Event<Boolean>>()
  val eventNavigateBack: LiveData<Event<Boolean>>
    get() = _eventNavigateBack

  fun onCancelClicked() {
    _eventNavigateBack.value = Event(true)
  }

  fun onSaveClicked() {
    val newShoe = collectInputs()
    if (newShoe != null) {
      _eventSaveShoe.value = Event(newShoe)
    }
  }

  private fun collectInputs(): Shoe? {
    var isAllInputValid = true

    val nameValidationResult = validateName()
    if (!nameValidationResult) {
      isAllInputValid = false
    }

    val sizeValidationResult = validateSize()
    if (!sizeValidationResult) {
      isAllInputValid = false
    }

    val companyValidationResult = validateCompany()
    if (!companyValidationResult) {
      isAllInputValid = false
    }

    val descriptionValidationResult = validateDescription()
    if (!descriptionValidationResult) {
      isAllInputValid = false
    }

    return if (isAllInputValid) {
      buildShoe()
    } else {
      null
    }
  }

  private fun buildShoe(): Shoe {
    return Shoe(
        name = name.value.orEmpty(),
        size = sizeString.value?.toDoubleOrNull() ?: 0.0,
        company = company.value.orEmpty(),
        description = description.value.orEmpty(),
        images = emptyList())
  }

  private fun validateName(): Boolean {
    return if (name.value.isNullOrEmpty()) {
      _errorName.value = Event(R.string.error_input_required)
      false
    } else {
      _errorName.value = Event(INPUT_VALIDATION_NO_ERROR)
      true
    }
  }

  private fun validateSize(): Boolean {
    val sizeValue = sizeString.value?.toDoubleOrNull() ?: 0.0
    return when {
      sizeString.value.isNullOrEmpty() -> {
        _errorSize.value = Event(R.string.error_input_required)
        false
      }
      sizeValue <= 0.0  -> {
        _errorSize.value = Event(R.string.error_shoe_size_invalid)
        false
      }
      else -> {
        _errorSize.value = Event(INPUT_VALIDATION_NO_ERROR)
        true
      }
    }
  }

  private fun validateCompany(): Boolean {
    return if (company.value.isNullOrEmpty()) {
      _errorCompany.value = Event(R.string.error_input_required)
      false
    } else {
      _errorCompany.value = Event(INPUT_VALIDATION_NO_ERROR)
      true
    }
  }

  private fun validateDescription(): Boolean {
    return if (description.value.isNullOrEmpty()) {
      _errorDescription.value = Event(R.string.error_input_required)
      false
    } else {
      _errorDescription.value = Event(INPUT_VALIDATION_NO_ERROR)
      true
    }
  }
}
