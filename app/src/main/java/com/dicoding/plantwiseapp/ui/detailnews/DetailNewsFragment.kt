package com.dicoding.plantwiseapp.ui.detailnews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dicoding.plantwiseapp.data.response.DataItem
import com.dicoding.plantwiseapp.databinding.FragmentDetailNewsBinding

class DetailNewsFragment : Fragment() {
    private var _binding: FragmentDetailNewsBinding? = null
    private val binding get() = _binding!!
    private val detailNewsFragmentViewModel by viewModels<DetailNewsFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsID = arguments?.getString(extraData)

        newsID.let {
            if (it != null) {
                detailNewsFragmentViewModel.searchNewsDetail(it)
            }
        }

        detailNewsFragmentViewModel.detailNews.observe(viewLifecycleOwner) {
            setData(it)
        }

//        detailNewsFragmentViewModel.isLoading.observe(viewLifecycleOwner) {
//            showLoading(it)
//        }
    }

    private fun setData(data: DataItem) {
        binding.apply {
            Glide.with(root.context)
                .load(data.imageUrl)
                .into(ivNews)
            tvTitle.text = data.tittle
            tvContent.text = data.content
        }
    }

    companion object {
        const val extraData = "EXTRA_ID"

        fun newInstance(newsId: Int): DetailNewsFragment {
            val fragment = DetailNewsFragment()
            val args = Bundle()
            args.putInt(extraData, newsId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}