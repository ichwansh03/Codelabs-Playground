package com.ichwan.gigihmodule.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.gigihmodule.databinding.DataItemBinding

class DataAdapter(val context: Context, private val list: ArrayList<ResponseModel>):
    RecyclerView.Adapter<DataAdapter.DataHolder>() {

    inner class DataHolder(private val binding: DataItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(names: String, usernames: String, emails: String){
            binding.apply {
                name.text = names
                username.text = usernames
                email.text = emails
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val binding = DataItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DataHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        holder.bind(list[position].name, list[position].username, list[position].email)
    }

    fun setData(data: ArrayList<ResponseModel>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}