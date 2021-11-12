package com.udacity.shoestore.ui.shoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.utils.Event

class ShoeListViewModel : ViewModel() {

  private val _eventAddShoe = MutableLiveData<Event<Boolean>>()
  val eventAddShoe: LiveData<Event<Boolean>> = _eventAddShoe

  fun onAddShoeClicked() {
    _eventAddShoe.value = Event(true)
  }
}
