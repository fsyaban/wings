package com.fachrul.wings.ui.checkout

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fachrul.wings.data.entity.relational.KeranjangProduct
import com.fachrul.wings.data.helper.Const
import com.fachrul.wings.databinding.FragmentCheckoutBinding
import com.fachrul.wings.view_model.CheckoutViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutFragment : Fragment() {
    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding
    private val adapter: CheckoutAdapter = CheckoutAdapter { product ->
        vm.subTotalMap[product.keranjangEntity.productCode] = product
        setTotalText()
    }
    private val vm: CheckoutViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCheckoutBinding.inflate(layoutInflater)
        initBinding()
        observeLiveData()
        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initBinding() {
        binding?.recycler?.adapter = adapter
        binding?.confirmButton?.setOnClickListener {
            if (vm.subTotalMap.isEmpty()) {
                Toast.makeText(requireContext(), "Harap pilih produk", Toast.LENGTH_SHORT).show()
            } else if (vm.isQuantityEmpty()) {
                Toast.makeText(requireContext(), "Quantity harap diisi", Toast.LENGTH_SHORT).show()
            } else {
                vm.insertTransaction()
                vm.deleteAllKeranjang()
                Toast.makeText(requireContext(), "Transaksi Berhasil", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }
        binding?.order?.setOnClickListener {
            findNavController().navigate(CheckoutFragmentDirections.toOrderFragment())
        }
    }

    @SuppressLint("SetTextI18n")
    fun observeLiveData() {
        vm.checkoutData.observe(viewLifecycleOwner) {
            vm.subTotalMap.clear()
            it.forEach { product->
                vm.subTotalMap[product.keranjangEntity.productCode] = product
            }
            adapter.differ.submitList(vm.subTotalMap.values.toList())
            setTotalText()
        }
    }

    @SuppressLint("SetTextI18n")
    fun setTotalText() {
        binding?.total?.text = "Rp. ${Const.getTotal(vm.subTotalMap.values.toList())}"

    }

    @SuppressLint("SetTextI18n")
    fun submitList(position:Int){
        adapter.notifyItemRemoved(position)
        binding?.total?.text = "Rp. ${adapter.differ.currentList.sumOf { Const.getSubTotal(it) }}"
    }

}