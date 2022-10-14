package com.fachrul.wings.ui.order.order_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fachrul.wings.data.entity.TransactionDetailEntity
import com.fachrul.wings.data.entity.relational.TransactionProduct
import com.fachrul.wings.data.entity.relational.TransactionRelation
import com.fachrul.wings.databinding.OrderDetailItemBinding


class OrderDetailAdapter() : RecyclerView.Adapter<OrderDetailAdapter.OrderDetailViewHolder>() {

    inner class OrderDetailViewHolder(val binding: OrderDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TransactionDetailEntity) {
            binding.productName.text = data.productCode
            binding.price.text = data.price.toString()
            binding.quantity.text =  data.quantity.toString()
            binding.subtotal.text =  data.subTotal.toString()
        }
    }

    val differ = AsyncListDiffer(this, itemCallback)

    companion object {
        val itemCallback = object : DiffUtil.ItemCallback<TransactionDetailEntity>(){
            override fun areItemsTheSame(
                oldItem: TransactionDetailEntity,
                newItem: TransactionDetailEntity
            ): Boolean = oldItem.documentCode==newItem.documentCode

            override fun areContentsTheSame(
                oldItem: TransactionDetailEntity,
                newItem: TransactionDetailEntity
            ): Boolean = false

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailViewHolder =
        OrderDetailViewHolder(
            OrderDetailItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: OrderDetailViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}