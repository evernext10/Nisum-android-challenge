package com.evernext10.marketplace.product.list.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.evernext10.core.domain.model.product.Product
import com.evernext10.core.domain.model.product.state.StateProductList
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

    private fun navigateToDetail(product: Product) {
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
            layoutManager = LinearLayoutManager(requireContext())
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
                if (it.isEmpty()) {
                    textViewNoData.visible(true)
                    textViewNoData.text = getString(R.string.set_word_in_search)
                } else {
                    textViewNoData.visible(false)
                    textViewNoData.text = getString(R.string.error_no_available_data)
                }
                productListViewModel.getMarketplaceProductList(it)
            }
            productListViewModel.productListState.observe(viewLifecycleOwner) {
                Log.i("ResponseStatus", it.toString())
                when (it) {
                    is StateProductList.Loading -> {
                        binding.progressBottom.visible(true)
                    }
                    is StateProductList.Success -> {
                        binding.progressBottom.visible(false)
                        adapterProductList.submitList(it.products)
                    }
                    is StateProductList.Unauthorized -> {
                        binding.progressBottom.visible(false)
                        Log.i("Response", "Unauthorized")
                    }
                    is StateProductList.Error -> {
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
