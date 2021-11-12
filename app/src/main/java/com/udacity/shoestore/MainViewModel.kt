package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainViewModel : ViewModel() {

  private val _shoes = MutableLiveData<List<Shoe>>()
  val shoes: LiveData<List<Shoe>>
    get() = _shoes

  fun addShoe(shoe: Shoe) {
    val newList = shoes.value?.toMutableList() ?: mutableListOf()
    newList.add(shoe)
    _shoes.value = newList
  }
}
