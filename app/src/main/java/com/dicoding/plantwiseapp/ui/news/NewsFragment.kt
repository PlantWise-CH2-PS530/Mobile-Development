package com.dicoding.plantwiseapp.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.plantwiseapp.R
import com.dicoding.plantwiseapp.data.response.DataItem
import com.dicoding.plantwiseapp.databinding.FragmentNewsBinding
import com.dicoding.plantwiseapp.ui.detailnews.DetailNewsFragment

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<NewsViewModel>()

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            val layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
            binding.rvHottestNews.layoutManager = layoutManager
            viewModel.findHottest()
            viewModel.hottestNews.observe(viewLifecycleOwner) { news ->
                setDataNews2(news)
            }


            val layoutManager2 = LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
            binding.rvRecentNews.layoutManager = layoutManager2
            viewModel.findRecent()
            viewModel.recentNews.observe(viewLifecycleOwner) { news ->
                setDataNews(news)
            }
        }
    }

    private fun setDataNews2(newsList: List<DataItem>) {
        Log.d("NewsFragment", "setDataNews2 called with ${newsList.size} items")
        val adapter = NewsAdapterHottest()
        adapter.submitList(newsList)
        binding.rvHottestNews.adapter = adapter

        adapter.setOnItemClickCallback(object : NewsAdapterHottest.OnItemClickCallback{
            override fun onItemClicked(data: DataItem) {
                val bundle = Bundle()
                bundle.putString(DetailNewsFragment.extraData, data.id)
                NavHostFragment
                    .findNavController(this@NewsFragment)
                    .navigate(R.id.action_navigation_news_to_detailNewsFragment, bundle)
            }
        })
    }

    private fun setDataNews(newsList: List<DataItem>) {
        Log.d("NewsFragment", "setDataNews called with ${newsList.size} items")
        val adapter = NewsAdapterRecent()
        adapter.submitList(newsList)
        binding.rvRecentNews.adapter = adapter

        adapter.setOnItemClickCallBack(object : NewsAdapterRecent.OnItemClickCallback{
            override fun onItemClicked(data: DataItem) {
                val bundle = Bundle()
                bundle.putString(DetailNewsFragment.extraData, data.id)
                NavHostFragment
                    .findNavController(this@NewsFragment)
                    .navigate(R.id.action_navigation_news_to_detailNewsFragment, bundle)
            }
        })
    }
}