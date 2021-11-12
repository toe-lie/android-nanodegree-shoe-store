package com.udacity.shoestore.utils

import java.text.DecimalFormat

object StringFormatter {
  @JvmStatic
  fun formatDecimal(number: Double): String {
    return DecimalFormat("#.#").format(number)
  }
}
