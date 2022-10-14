package com.fachrul.wings.ui.checkout

import android.annotation.SuppressLint
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fachrul.wings.data.entity.relational.KeranjangProduct
import com.fachrul.wings.data.entity.ProductEntity
import com.fachrul.wings.data.helper.Const
import com.fachrul.wings.databinding.CheckoutItemBinding

class CheckoutAdapter(val sumTotal: (KeranjangProduct) -> Unit) :
    RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder>() {
    inner class CheckoutViewHolder(val binding: CheckoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: KeranjangProduct) {
            binding.productName.text = data.productEntity.productName
            binding.unit.text = data.productEntity.unit
            binding.minus.isEnabled = false
            binding.quantity.addTextChangedListener {
                if (!it.isNullOrEmpty()){
                    binding.minus.isEnabled = it.toString().toInt() >= 2
                    data.keranjangEntity.quantity = it.toString().toInt()
                }
                else{
                    data.keranjangEntity.quantity = 0
                    binding.minus.isEnabled = false
                }

                binding.subtotal.text = "Rp. ${Const.getSubTotal(data)}"
                sumTotal(data)
            }
            binding.add.setOnClickListener {
                data.keranjangEntity.quantity+=1
                binding.quantity.setText(data.keranjangEntity.quantity.toString())
            }
            binding.minus.setOnClickListener {
                data.keranjangEntity.quantity-=1
                binding.quantity.setText(data.keranjangEntity.quantity.toString())
            }
            binding.quantity.setText(data.keranjangEntity.quantity.toString())
        }
    }

    val differ = AsyncListDiffer(this, itemCallback)

    companion object {
        val itemCallback = object : DiffUtil.ItemCallback<KeranjangProduct>() {
            override fun areItemsTheSame(
                oldItem: KeranjangProduct,
                newItem: KeranjangProduct
            ): Boolean =
                oldItem.productEntity.productCode == newItem.productEntity.productCode


            override fun areContentsTheSame(
                oldItem: KeranjangProduct,
                newItem: KeranjangProduct
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

    override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}