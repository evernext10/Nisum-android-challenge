package com.evernext10.marketplace.product.detail.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.evernext10.core.domain.model.pokemon.Pokemon
import com.evernext10.core.domain.model.pokemon.response.MarketplaceProductDetailResponse
import com.evernext10.core.domain.model.pokemon.response.Stats
import com.evernext10.core.domain.model.pokemon.state.StateProductDetail
import com.evernext10.core.ext.*
import com.evernext10.marketplace.product.detail.presentation.R
import com.evernext10.marketplace.product.detail.presentation.adapter.StatsAdapter
import com.evernext10.marketplace.product.detail.presentation.databinding.FragmentProductDetailScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailScreen : Fragment() {

    private var _binding: FragmentProductDetailScreenBinding? = null
    private val binding get() = _binding!!
    private val adapter = StatsAdapter()

    private val pockemon by lazy {
        requireArguments()["pockemon"] as Pokemon
    }
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
        detailProductDetailViewModel.getDataFromStateHandled(pockemon.id!!)
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
                        binding.progressCircular.visible(true)
                    }
                    is StateProductDetail.Success -> {
                        binding.progressCircular.visible(false)
                        initViews(it.response)
                    }
                    is StateProductDetail.Unauthorized -> {
                        binding.progressCircular.visible(true)
                        Log.i("Response", "Unauthorized")
                    }
                    is StateProductDetail.Error -> {
                        binding.progressCircular.visible(false)
                        showAlertDialogErrorApi()
                    }
                    else -> {
                        binding.progressCircular.visible(false)
                        Log.i("Response", "Unknow")
                    }
                }
            }
        }
    }

    private fun initViews(product: MarketplaceProductDetailResponse) = with(binding) {
        toolbar.apply {
            inflateMenu(R.menu.product_item_menu)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

        pokemonItemImage.bindImageUrl(
            url = pockemon.url?.pokemonImageUrl(),
            placeholder = com.evernext10.core.R.drawable.ic_baseline_rotate_left_24,
            errorPlaceholder = com.evernext10.core.R.drawable.ic_baseline_error_24
        )

        progressCircular.visible(false)
        (product.weight?.div(10.0).toString() + " kgs").also { weight ->
            pokemonItemWeight.text = weight
        }
        (product.height?.div(10.0).toString() + " metres").also { height ->
            pokemonItemHeight.text = height
        }
        pokemonStatList.adapter = adapter
        adapter.setStats(product.stats as ArrayList<Stats>)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
