package com.pss.djmw_admin.view.main.post

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.pss.djmw_admin.R
import com.pss.djmw_admin.base.BaseFragment
import com.pss.djmw_admin.databinding.FragmentPostBinding
import com.pss.djmw_admin.view.main.post.adapter.PostRecyclerViewAdapter
import com.pss.djmw_admin.viewmodel.MainViewModel
import com.pss.djmw_admin.viewmodel.PostViewModel
import com.pss.djmw_admin.widget.extension.settingVisibility
import com.pss.djmw_admin.widget.extension.showVertical

class PostFragment : BaseFragment<FragmentPostBinding>(R.layout.fragment_post) {
    private val postViewModel by activityViewModels<PostViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun init() {
        binding.fragment = this
        postViewModel.initArrayList()
        initTypeWriterTextAnim()
        initGet()
        initSwipeLayout()
        observeViewModel()
    }

    private fun initTypeWriterTextAnim() {
        binding.loadingTxt.apply {
            setDelay(1)
            setWithMusic(false)
            animateText("고민을 귀담아듣는 중")
        }
        binding.content.apply {
            setDelay(1)
            setWithMusic(false)
            animateText("때로는 털어놓는 게 해결 방법입니다!")
        }
    }

    private fun initGet() {
        if (postViewModel.postList.isNullOrEmpty()){
            postViewModel.getPost()
        }else initPostView()
    }

    private fun observeViewModel() {
        postViewModel.eventGetPostSuccess.observe(this, {
            binding.swipeLayout.isRefreshing = false
            initPostView()
        })

        postViewModel.eventError.observe(this,{
            binding.swipeLayout.isRefreshing = false
            when(it){
                0 -> shortShowToast("게시물을 가져오는데 오류가 발생했습니다")
            }
        })
    }

    private fun initSwipeLayout(){
        binding.swipeLayout.setOnRefreshListener {
            postViewModel.getPost()
        }
    }

    private fun initPostView(){
        binding.loadingBar.settingVisibility(false)
        binding.loadingTxt.settingVisibility(false)
        binding.swipeLayout.visibility = View.VISIBLE
        initRecyclerView()
    }

    fun clickSetPostBtn(view: View){
        mainViewModel.setActionView(false)
        this.findNavController().navigate(R.id.action_postFragment_to_setPostFragment)
    }

    private fun initRecyclerView() {
        binding.postRecyclerView.showVertical(requireContext())
        binding.postRecyclerView.adapter = PostRecyclerViewAdapter(postViewModel)
    }
}