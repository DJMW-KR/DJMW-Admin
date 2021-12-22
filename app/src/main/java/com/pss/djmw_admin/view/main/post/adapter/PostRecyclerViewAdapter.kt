package com.pss.djmw_admin.view.main.post.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pss.djmw_admin.R
import com.pss.djmw_admin.data.model.Post
import com.pss.djmw_admin.databinding.PostRecyclerViewItemBinding
import com.pss.djmw_admin.viewmodel.PostViewModel


class PostRecyclerViewAdapter(
    val viewModel: PostViewModel
) : RecyclerView.Adapter<PostRecyclerViewAdapter.PostRecyclerViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<PostRecyclerViewItemBinding>(
            layoutInflater,
            R.layout.post_recycler_view_item,
            parent,
            false
        )
        return PostRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostRecyclerViewHolder, position: Int) {
        holder.bind(viewModel.postList[position])
    }

    override fun getItemCount(): Int {
        return viewModel.postList.size
    }

    inner class PostRecyclerViewHolder(val binding: PostRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Post) {
            binding.data = data
            binding.executePendingBindings()
        }
    }
}