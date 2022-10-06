package com.fachrul.wings.ui.checkout

import android.annotation.SuppressLint
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fachrul.wings.data.entity.ProductEntity
import com.fachrul.wings.databinding.CheckoutItemBinding

class CheckoutAdapter : RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder>() {
    inner class CheckoutViewHolder(val binding: CheckoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            @SuppressLint("SetTextI18n")
            fun bind(data:ProductEntity){
                binding.productName.text = data.productName
                binding.unit.text = data.unit
                binding.quantity.addTextChangedListener {
                    if(!it.isNullOrEmpty()){
                        binding.subtotal.text = "Rp. ${(Integer.valueOf(it.toString()))*data.price}"
                    }
                    else binding.subtotal.text = "Rp. 0"
                }
                binding.quantity.setText("1")
            }
    }

    val differ = AsyncListDiffer(this, itemCallback)

    companion object {
        val itemCallback = object : DiffUtil.ItemCallback<ProductEntity>() {
            override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean =
                oldItem.productCode == oldItem.productCode

            override fun areContentsTheSame(
                oldItem: ProductEntity,
                newItem: ProductEntity
            ): Boolean = false

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutViewHolder =
        CheckoutViewHolder(
            CheckoutItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int){
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}