package com.fachrul.wings.ui.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fachrul.wings.databinding.FragmentCheckoutBinding
import com.fachrul.wings.databinding.FragmentDetailBinding
import com.fachrul.wings.view_model.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutFragment:Fragment() {
    private lateinit var vm :SharedViewModel
    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding
    private val adapter:CheckoutAdapter = CheckoutAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCheckoutBinding.inflate(layoutInflater)
        vm = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        initBinding()
        return binding?.root
    }

    fun initBinding(){
        binding?.recycler?.adapter = adapter
        adapter.differ.submitList(vm.listProductChart)
    }
}