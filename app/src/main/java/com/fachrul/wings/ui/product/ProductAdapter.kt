package com.fachrul.wings.ui.product

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fachrul.wings.data.entity.ProductEntity
import com.fachrul.wings.databinding.ProductItemBinding

class ProductAdapter(val navigateToProduct:(ProductEntity)->Unit) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: ProductEntity) {
            binding.productName.text = data.productName

            if (data.discount.toString().equals("0.0")){
                binding.discount.visibility = View.GONE
                binding.price.text = "Rp. ${data.price}"
            } else {
                binding.discount.text = data.price.toString()
                binding.discount.paintFlags= Paint.STRIKE_THRU_TEXT_FLAG
                binding.price.text = "Rp. ${(data.price - (data.price*data.discount/100))}"
            }
            binding.buyButton.setOnClickListener {
                navigateToProduct(data)
            }

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}