package com.fachrul.wings.ui.order.order_detail

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.fachrul.wings.data.entity.TransactionDetailEntity
import com.fachrul.wings.data.entity.relational.TransactionProduct
import com.fachrul.wings.databinding.FragmentOrderBinding
import com.fachrul.wings.databinding.FragmentOrderDetailBinding
import com.fachrul.wings.view_model.OrderDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class OrderDetailFragment : Fragment() {
    private var _binding: FragmentOrderDetailBinding? = null
    private val binding get() = _binding
    private val vm: OrderDetailViewModel by viewModels()
    private val navArgs by navArgs<OrderDetailFragmentArgs>()
    private val adapter = OrderDetailAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderDetailBinding.inflate(layoutInflater)
        initBinding()
        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    fun initBinding() {
        binding?.recycler?.adapter = adapter
        lifecycleScope.launch {
            adapter.differ.submitList(navArgs.transaction.transactionDetail)
        }
        binding?.tanggal?.text = navArgs.transaction.transactionHeaderEntity.date.substring(0,10)
        binding?.total?.text = "Rp. ${navArgs.transaction.transactionHeaderEntity.total}"
    }

}