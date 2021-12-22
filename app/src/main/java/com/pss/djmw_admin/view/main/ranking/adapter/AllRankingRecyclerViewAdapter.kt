package com.pss.djmw_admin.view.main.ranking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pss.djmw_admin.R
import com.pss.djmw_admin.data.model.UserInfo
import com.pss.djmw_admin.databinding.AroundRankingRecyclerViewItemBinding
import com.pss.djmw_admin.viewmodel.MainViewModel

class AllRankingRecyclerViewAdapter(
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<AllRankingRecyclerViewAdapter.AroundRankingRecyclerViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AroundRankingRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<AroundRankingRecyclerViewItemBinding>(
            layoutInflater,
            R.layout.around_ranking_recycler_view_item,
            parent,
            false
        )
        return AroundRankingRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AroundRankingRecyclerViewHolder, position: Int) {
        holder.binding.ranking.text = (position + 1).toString()
        holder.bind(viewModel.userRankingList[position])
    }

    override fun getItemCount(): Int {
        return viewModel.userRankingList.size
    }

    inner class AroundRankingRecyclerViewHolder(val binding: AroundRankingRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserInfo) {
            binding.data = data
            binding.executePendingBindings()
        }
    }
}