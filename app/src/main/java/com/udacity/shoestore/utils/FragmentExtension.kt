package com.udacity.shoestore.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.udacity.shoestore.R

fun Fragment.setHomeAsCloseIndicator() {
  AppCompatResources.getDrawable(requireContext(), R.drawable.ic_close_white_24)?.let {
    (requireActivity() as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(it)
  }
}
