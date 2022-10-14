package com.fachrul.wings.ui.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fachrul.wings.data.entity.relational.TransactionRelation
import com.fachrul.wings.databinding.OrderItemBinding

class OrderAdapter(val navigateToDetail:(TransactionRelation)->Unit) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(val binding: OrderItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data:TransactionRelation){
            binding.total.text = data.transactionHeaderEntity.total.toString()
            binding.date.text = data.transactionHeaderEntity.date
            binding.root.setOnClickListener {
                navigateToDetail(data)
            }
        }
    }

    val differ =  AsyncListDiffer(this, diffUtil)

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<TransactionRelation>() {
            override fun areItemsTheSame(oldItem: TransactionRelation, newItem: TransactionRelation): Boolean =
                oldItem.transactionHeaderEntity.documentCode == newItem.transactionHeaderEntity.documentCode

            override fun areContentsTheSame(oldItem: TransactionRelation, newItem: TransactionRelation): Boolean =
                false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder =
        OrderViewHolder(OrderItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}