package com.udacity.shoestore.ui.onboarding.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.utils.Event

class WelcomeViewModel : ViewModel() {

  private val _eventNavigateNext = MutableLiveData<Event<Boolean>>()
  val eventNavigateNext: LiveData<Event<Boolean>> = _eventNavigateNext

  fun onNextClicked() {
    _eventNavigateNext.value = Event(true)
  }
}
