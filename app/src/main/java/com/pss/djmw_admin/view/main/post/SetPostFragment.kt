package com.pss.djmw_admin.view.main.post

import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.pss.djmw_admin.R
import com.pss.djmw_admin.base.BaseFragment
import com.pss.djmw_admin.data.model.Post
import com.pss.djmw_admin.databinding.FragmentSetPostBinding
import com.pss.djmw_admin.viewmodel.MainViewModel
import com.pss.djmw_admin.viewmodel.PostViewModel
import com.pss.djmw_admin.widget.extension.settingVisibility
import java.text.SimpleDateFormat
import java.util.*


class SetPostFragment : BaseFragment<FragmentSetPostBinding>(R.layout.fragment_set_post) {
    private val mainViewModel by activityViewModels<MainViewModel>()
    private val postViewModel by activityViewModels<PostViewModel>()
    private lateinit var callback: OnBackPressedCallback


/*    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("로그","setPost 뒤로가기 버튼 눌림")
                mainViewModel.setActionView(true)
                findNavController().navigate(R.id.action_setPostFragment_to_postFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }*/

    override fun init() {
        binding.fragment = this
        observeViewModel()
    }

    private fun observeViewModel(){
        postViewModel.eventError.observe(this,{
            binding.uploadBtn.isEnabled = true
            binding.loadingBar.settingVisibility(false)
            when(it){
                1 -> shortShowToast("고민글 업로드에 실패했습니다")
            }
        })

        postViewModel.eventSetPostSuccess.observe(this,{
            mainViewModel.setActionView(true)
            binding.uploadBtn.isEnabled = true
            binding.loadingBar.settingVisibility(false)
            this.findNavController().popBackStack()
        })
    }

    fun clickBackBtn(view: View){
        mainViewModel.setActionView(true)
        this.findNavController().popBackStack()
    }

    fun clickUploadBtn(view: View){
        binding.uploadBtn.isEnabled = false
        binding.loadingBar.settingVisibility(true)
        postViewModel.setPost(Post(binding.title.text.toString(), binding.content.text.toString(), nowTime()))
    }

    private fun nowTime(): String = SimpleDateFormat("yyyy.MM.dd HH시 mm분", Locale("ko", "KR")).format(Date(System.currentTimeMillis()))
}