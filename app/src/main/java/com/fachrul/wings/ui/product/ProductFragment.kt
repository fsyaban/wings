package com.fachrul.wings.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fachrul.wings.databinding.FragmentProductBinding
import com.fachrul.wings.view_model.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment: Fragment() {
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding
    private val vm: ProductViewModel by viewModels()
    private val adapter:ProductAdapter = ProductAdapter(){
        findNavController().navigate(ProductFragmentDirections.actionProductFragmentToDetailFragment(it))
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(layoutInflater)
        initBinding()
        observeLiveData()
        return binding?.root
    }

    fun initBinding(){
        binding?.recycler?.adapter = adapter
        binding?.checkout?.setOnClickListener {
            findNavController().navigate(ProductFragmentDirections.productToCheckout())
        }
    }

    fun observeLiveData(){
        vm.productLiveData?.observe(viewLifecycleOwner){
            adapter.differ.submitList(it)
        }
    }
}