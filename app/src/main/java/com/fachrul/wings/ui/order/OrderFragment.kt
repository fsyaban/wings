package com.fachrul.wings.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fachrul.wings.databinding.FragmentLoginBinding
import com.fachrul.wings.databinding.FragmentOrderBinding
import com.fachrul.wings.view_model.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment:Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding
    private val vm:OrderViewModel by viewModels()
    private val adapter= OrderAdapter(){
        findNavController().navigate(OrderFragmentDirections.toOrderDetail(it))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentOrderBinding.inflate(layoutInflater)
        initBinding()
        observeLiveData()
        return binding?.root
    }

    fun initBinding(){
        binding?.recycler?.adapter = adapter
    }

    fun observeLiveData(){
        vm.transactionLiveData.observe(viewLifecycleOwner){
            adapter.differ.submitList(it)
        }
    }
}