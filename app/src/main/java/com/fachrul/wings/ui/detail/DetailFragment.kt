package com.fachrul.wings.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.fachrul.wings.databinding.FragmentDetailBinding
import com.fachrul.wings.view_model.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding
    private val navArgs: DetailFragmentArgs by navArgs()
    private val vm: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        initBinding()
        return binding?.root
    }

    @SuppressLint("SetTextI18n")
    fun initBinding() {
        binding?.productName?.text = navArgs.product.productName
        binding?.dimension?.text = navArgs.product.dimension
        binding?.priceUnit?.text = navArgs.product.unit
        binding?.price?.text =
            "Rp. ${(navArgs.product.price - (navArgs.product.price * navArgs.product.discount / 100))}"
        binding?.buy?.setOnClickListener {
            vm.insertProduct(navArgs.product.productCode)
            Toast.makeText(
                requireContext(),
                "Produk telah ditambahkan di keranjang",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}