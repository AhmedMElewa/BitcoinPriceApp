package com.elewa.sampleandroidapp.modules.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elewa.sampleandroidapp.core.extention.toDate
import com.elewa.sampleandroidapp.core.extention.toFormattedString
import com.elewa.sampleandroidapp.databinding.ItemPriceBinding
import com.elewa.sampleandroidapp.modules.home.domain.entites.CoinDeskEntity
import com.elewa.sampleandroidapp.modules.home.presentation.uimodel.CoinDeskUiModel
import javax.inject.Inject

class CoinDeskAdapter @Inject constructor() :
    ListAdapter<CoinDeskUiModel, CoinDeskAdapter.MyViewHolder>(MyDiffUtil) {

    companion object MyDiffUtil : DiffUtil.ItemCallback<CoinDeskUiModel>() {
        override fun areItemsTheSame(oldItem: CoinDeskUiModel, newItem: CoinDeskUiModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CoinDeskUiModel, newItem: CoinDeskUiModel): Boolean {
            return oldItem.date == newItem.date
        }
    }

    inner class MyViewHolder(private val binding: ItemPriceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CoinDeskUiModel?) {
            item?.let {
                binding.txtDate.text = item.date.toString()
                binding.txtRate.text = item.rate
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemPriceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}