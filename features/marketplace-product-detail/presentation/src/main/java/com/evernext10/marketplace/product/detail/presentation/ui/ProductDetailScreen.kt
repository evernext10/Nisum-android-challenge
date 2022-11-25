package com.evernext10.marketplace.product.detail.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.evernext10.core.domain.model.pokemon.Pokemon
import com.evernext10.core.domain.model.pokemon.state.StateProductDetail
import com.evernext10.core.ext.launchAndRepeatWithViewLifecycle
import com.evernext10.core.ext.showAlertDialogErrorApi
import com.evernext10.core.ext.toFormattedNumber
import com.evernext10.core.ext.visible
import com.evernext10.marketplace.product.detail.presentation.R
import com.evernext10.marketplace.product.detail.presentation.databinding.FragmentProductDetailScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailScreen : Fragment() {

    private var _binding: FragmentProductDetailScreenBinding? = null
    private val binding get() = _binding!!

    private val detailProductDetailViewModel by viewModel<ProductDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("STATE_INSTANCE", "Saved")
    }
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.i("STATE_INSTANCE", "Restored")
        detailProductDetailViewModel.getDataFromStateHandled(requireArguments()["productId"] as String)
        observerState()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.product_item_menu, menu)
    }

    private fun observerState() {
        launchAndRepeatWithViewLifecycle {
            detailProductDetailViewModel.productDetailState.observe(viewLifecycleOwner) {
                Log.i("ResponseStatus", it.toString())
                when (it) {
                    is StateProductDetail.Loading -> {
                        binding.progress.visible(true)
                    }
                    is StateProductDetail.Success -> {
                        binding.progress.visible(false)
                        initViews(it.product)
                    }
                    is StateProductDetail.Unauthorized -> {
                        binding.progress.visible(true)
                        Log.i("Response", "Unauthorized")
                    }
                    is StateProductDetail.Error -> {
                        binding.progress.visible(false)
                        showAlertDialogErrorApi()
                    }
                    else -> {
                        binding.progress.visible(false)
                        Log.i("Response", "Unknow")
                    }
                }
            }
        }
    }

    private fun initViews(product: Pokemon) = with(binding) {
        productTitle.text = product.name

        toolbar.apply {
            inflateMenu(R.menu.product_item_menu)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
