package com.hzr.kemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter4.BaseQuickAdapter
import com.hzr.kemo.databinding.ItemDrinkBinding
import com.hzr.kemo.model.DrinkListEntity

class DrinkListAdapter : BaseQuickAdapter<DrinkListEntity, DrinkListAdapter.DrinkListVH>() {
    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): DrinkListVH
        = DrinkListVH(ItemDrinkBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))


    override fun onBindViewHolder(
        holder: DrinkListVH,
        position: Int,
        item: DrinkListEntity?
    ) {
        item?.let {
           holder.binding.apply {
               colorView.setBackgroundColor(item.bgColor)
               tvName.text = item.title
               tvDesc.text = item.desc
               tvPrice.text = item.price.toString()
               tvScore.text = item.likeStar.toString()
           }
        }

    }

    inner class DrinkListVH(val binding: ItemDrinkBinding): RecyclerView.ViewHolder(binding.root)
}