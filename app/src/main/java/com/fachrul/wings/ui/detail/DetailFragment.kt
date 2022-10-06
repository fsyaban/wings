package com.fachrul.wings.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.fachrul.wings.databinding.FragmentDetailBinding
import com.fachrul.wings.databinding.FragmentLoginBinding
import com.fachrul.wings.ui.MainActivity
import com.fachrul.wings.view_model.LoginViewModel
import com.fachrul.wings.view_model.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment:Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding
    private lateinit var vm: SharedViewModel
    private val navArgs:DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        vm = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        initBinding()
        return binding?.root
    }

    @SuppressLint("SetTextI18n")
    fun initBinding(){
        binding?.productName?.text = navArgs.product.productName
        binding?.dimension?.text = navArgs.product.dimension
        binding?.priceUnit?.text = navArgs.product.unit
        binding?.price?.text = " Rp. ${navArgs.product.price}"
        binding?.buy?.setOnClickListener {
            if (!vm.listProductChart.contains(navArgs.product)){
                vm.listProductChart.add(navArgs.product)
                Toast.makeText(requireContext(),"Produk telah ditambahkan di keranjang",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(),"Produk sudah ditambahkan",Toast.LENGTH_SHORT).show()
            }
        }
    }
}