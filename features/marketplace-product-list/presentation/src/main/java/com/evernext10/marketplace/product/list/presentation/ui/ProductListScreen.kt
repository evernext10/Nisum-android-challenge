package com.evernext10.marketplace.product.list.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.evernext10.core.domain.model.pokemon.Pokemon
import com.evernext10.core.domain.model.pokemon.state.StatePokemonList
import com.evernext10.core.ext.launchAndRepeatWithViewLifecycle
import com.evernext10.core.ext.onQueryTextChanged
import com.evernext10.core.ext.showAlertDialogErrorApi
import com.evernext10.core.ext.visible
import com.evernext10.marketplace.product.list.presentation.R
import com.evernext10.marketplace.product.list.presentation.adapter.ProductListAdapter
import com.evernext10.marketplace.product.list.presentation.databinding.FragmentProductListScreenBinding
import com.evernext10.navigation.Destination
import com.evernext10.navigation.navigateToDestination
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListScreen : Fragment() {

    private val productListViewModel by viewModel<ProductListScreenViewModel>()

    private var _binding: FragmentProductListScreenBinding? = null
    private val binding get() = _binding!!

    private val adapterProductList: ProductListAdapter by lazy {
        ProductListAdapter(onClick = {
            navigateToDetail(it)
        })
    }

    private fun navigateToDetail(product: Pokemon) {
        navigateToDestination(
            Destination.ProductDetail(product.id!!)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        observerState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("STATE_INSTANCE", "Saved")
    }
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.i("STATE_INSTANCE", "Restored")
        productListViewModel.getDataFromStateHandled()
        observerState()
    }

    private fun initViews() = with(binding) {
        recyclerViewProducts.apply {
            adapter = adapterProductList
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        searchProduct.apply {
            queryHint = HtmlCompat.fromHtml(
                "<font color = #000000>" + resources.getString(R.string.search_bar_title) + "</font>",
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
            query
            onQueryTextChanged {
                progressBottom.visible(true)
                productListViewModel.postQueryWord(it)
            }
        }
    }

    private fun observerState() = with(binding) {
        launchAndRepeatWithViewLifecycle {
            productListViewModel.queryWord.observe(viewLifecycleOwner) {
                // productListViewModel.getMarketplaceProductList(it)
            }
            productListViewModel.productListState.observe(viewLifecycleOwner) {
                Log.i("ResponseStatus", it.toString())
                when (it) {
                    is StatePokemonList.Loading -> {
                        binding.progressBottom.visible(true)
                    }
                    is StatePokemonList.Success -> {
                        binding.progressBottom.visible(false)
                        adapterProductList.submitList(it.pokemon)
                    }
                    is StatePokemonList.Unauthorized -> {
                        binding.progressBottom.visible(false)
                        Log.i("Response", "Unauthorized")
                    }
                    is StatePokemonList.Error -> {
                        showAlertDialogErrorApi()
                    }
                    else -> {
                        Log.i("Response", "Unknow")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
