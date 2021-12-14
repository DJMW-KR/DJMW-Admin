package com.pss.djmw_android.view.main.ranking


import androidx.fragment.app.activityViewModels
import com.pss.djmw_android.R
import com.pss.djmw_android.base.BaseFragment
import androidx.viewpager2.widget.ViewPager2
import com.pss.djmw_android.databinding.FragmentRankingBinding
import com.pss.djmw_android.view.main.ranking.adapter.AllRankingRecyclerViewAdapter
import com.pss.djmw_android.view.main.ranking.adapter.RankingViewPagerAdapter
import com.pss.djmw_android.viewmodel.MainViewModel
import com.pss.djmw_android.widget.extension.showVertical


class RankingFragment : BaseFragment<FragmentRankingBinding>(R.layout.fragment_ranking) {
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun init() {
        initViewPager()
        initRecyclerView()
    }

    private fun initViewPager() {
        val imgList = arrayListOf<Int>(R.drawable.first, R.drawable.second, R.drawable.third)
        binding.viewPager2.run {
            adapter = RankingViewPagerAdapter(mainViewModel, requireContext(), imgList)
            setPadding(30, 0, 30, 0)
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }
            })
        }
    }

    private fun initRecyclerView() {
        binding.aroundRankingRecyclerView.showVertical(requireContext())
        binding.aroundRankingRecyclerView.adapter = AllRankingRecyclerViewAdapter(mainViewModel)
    }
}