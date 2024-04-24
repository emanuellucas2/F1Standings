package com.develrm.f1standings.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.develrm.f1standings.data.model.ConstructorModel
import com.develrm.f1standings.databinding.ItemConstructorBinding

class ConstructorAdapter : RecyclerView.Adapter<ConstructorAdapter.ConstructorViewHolder>() {

    inner class ConstructorViewHolder(val binding: ItemConstructorBinding):
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<ConstructorModel>(){
        override fun areItemsTheSame(oldItem: ConstructorModel, newItem: ConstructorModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: ConstructorModel, newItem: ConstructorModel): Boolean {
            return oldItem.position == newItem.position && oldItem.constructor == newItem.constructor &&
                    oldItem.points == newItem.points && oldItem.urlImage == newItem.urlImage
        }


    }

    private val differ = AsyncListDiffer(this,differCallback)

    var constructor: List<ConstructorModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConstructorViewHolder {
        return ConstructorViewHolder(
            ItemConstructorBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun getItemCount(): Int = constructor.size

    override fun onBindViewHolder(holder: ConstructorViewHolder, position: Int) {
        val constructor = constructor[position]
        holder.binding.apply {
            tvName.text = constructor.constructor
            tvPoints.text = constructor.points
            tvPosition.text = constructor.position

            Glide.with(imgConstructor)
                .load(constructor.urlImage)
                .into(imgConstructor)
        }
    }
}