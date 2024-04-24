package com.develrm.f1standings.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.develrm.f1standings.data.model.DriverModel
import com.develrm.f1standings.databinding.ItemDriverBinding

class DriverAdapter : RecyclerView.Adapter<DriverAdapter.DriverViewHolder>() {

    inner class DriverViewHolder(val binding: ItemDriverBinding):
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<DriverModel>(){
        override fun areItemsTheSame(oldItem: DriverModel, newItem: DriverModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: DriverModel, newItem: DriverModel): Boolean {
            return oldItem.position == newItem.position && oldItem.name == newItem.name &&
                    oldItem.points == newItem.points && oldItem.constructorName == newItem.constructorName
                    && oldItem.urlImage == newItem.urlImage
        }


    }

    private val differ = AsyncListDiffer(this,differCallback)

    var drivers: List<DriverModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        return DriverViewHolder(
            ItemDriverBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun getItemCount(): Int = drivers.size

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        val driver = drivers[position]
        holder.binding.apply {
            tvName.text = driver.name
            tvConstructorName.text = driver.constructorName
            tvPoints.text = driver.points
            tvPosition.text = driver.position

            Glide.with(imgDriver)
                .load(driver.urlImage)
                .into(imgDriver)
        }
    }
}