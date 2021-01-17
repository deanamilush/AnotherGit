package com.dean.anothergit

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dean.anothergit.databinding.ItemUserBinding

class DataUsersAdapter (private val listDataUsers : ArrayList<DataUsers>): RecyclerView.Adapter<DataUsersAdapter.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(items: ArrayList<DataUsers>) {
        listDataUsers.clear()
        listDataUsers.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataUsersAdapter.ListViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataUsersAdapter.ListViewHolder, position: Int) {
        holder.bind(listDataUsers[position])
        val data = listDataUsers[position]
        holder.itemView.setOnClickListener {
            val dataUserIntent = DataUsers(
                data.username,
                data.username,
                data.avatar,
                data.company,
                data.location,
                data.repository,
                data.followers,
                data.following
            )
            val mIntent = Intent(it.context, DetailActivity::class.java)
            mIntent.putExtra(DetailActivity.EXTRA_DETAIL, dataUserIntent)
            it.context.startActivity(mIntent)
        }


    }

    override fun getItemCount(): Int = listDataUsers.size

    inner class ListViewHolder (private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (user: DataUsers){
            with(binding){
                Glide.with(itemView.context)
                    .load(user.avatar)
                    .apply(RequestOptions().override(55,55))
                    .into(avatar)

                fullName.text = user.name
                username.text = user.username

            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataUsers)
    }
}