package com.udacity.shoestore.ui.shoelist

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Space
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.databinding.ShoeListItemBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.utils.EventObserver

class ShoeListFragment : Fragment() {

  private var _binding: ShoeListFragmentBinding? = null
  private val binding: ShoeListFragmentBinding
    get() = _binding!!

  private val viewModel by viewModels<ShoeListViewModel>()

  private val mainViewModel by activityViewModels<MainViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    _binding = DataBindingUtil.inflate(inflater, R.layout.shoe_list_fragment, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    initViewModel()
    observeViewModel()
    observeEvents()
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    inflater.inflate(R.menu.logout, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val handled = NavigationUI.onNavDestinationSelected(item, findNavController())
    if (!handled) {
      when(item.itemId) {
        R.id.action_logout -> {
          doLogout()
        }
      }
    }
    return true
  }

  private fun doLogout() {
    findNavController().graph = findNavController().graph
  }

  private fun initViewModel() {
    binding.viewModel = viewModel
    binding.lifecycleOwner = viewLifecycleOwner
  }

  private fun observeViewModel() {
    mainViewModel.shoes.observe(
        viewLifecycleOwner,
        { list ->
          binding.shoeListContainer.isVisible = list.isNotEmpty()
          binding.emptyView.isVisible = list.isEmpty()
          buildShoeList(list.reversed())
        })
  }

  private fun observeEvents() {
    viewModel.eventAddShoe.observe(
        viewLifecycleOwner,
        EventObserver { isAdd ->
          if (isAdd) {
            navigateToShoeEntryScreen()
          }
        })
  }

  private fun buildShoeList(list: List<Shoe>?) {
    binding.shoeListContainer.removeAllViews()
    list?.forEach { shoe ->
      val itemView = ShoeListItemBinding.inflate(layoutInflater, null, false)
      itemView.shoe = shoe
      binding.shoeListContainer.addView(itemView.root)
      binding.shoeListContainer.addView(createSpacing())
    }
  }

  private fun navigateToShoeEntryScreen() {
    findNavController()
        .navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
  }

  private fun createSpacing(): View {
    val space = Space(context)
    val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, resources.getDimensionPixelSize(R.dimen.spacing_normal))
    space.layoutParams = layoutParams
    return space
  }

}
