package com.pss.djmw_admin.view.main.ranking.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pss.djmw_admin.R
import com.pss.djmw_admin.data.model.UserInfo
import com.pss.djmw_admin.databinding.RankingViewPagerItemBinding
import com.pss.djmw_admin.viewmodel.MainViewModel

class RankingViewPagerAdapter(
    val viewModel: MainViewModel,
    var context: Context,
    val imgList: ArrayList<Int>
) : RecyclerView.Adapter<RankingViewPagerAdapter.RankingViewPagerViewHolder>() {


    override fun getItemCount(): Int {
        return if (viewModel.userRankingList.size > 3) 3 else viewModel.userRankingList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewPagerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RankingViewPagerItemBinding>(
            layoutInflater,
            R.layout.ranking_view_pager_item,
            parent,
            false
        )
        return RankingViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RankingViewPagerViewHolder, position: Int) {
        holder.binding.rankingImg.apply {
            setImageResource(imgList[position])
            setColorFilter(Color.parseColor("#FFFFFF"))
        }

        holder.bind(viewModel.userRankingList[position])
    }

    inner class RankingViewPagerViewHolder(val binding: RankingViewPagerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserInfo) {
            binding.data = data
            binding.executePendingBindings()
        }
    }
}