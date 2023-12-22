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

class NewsFragment : Fragment() {

    private var binding: FragmentNewsBinding? = null
    private val viewModel: NewsViewModel by viewModels()

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            val layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
            binding!!.rvHottestNews.layoutManager = layoutManager

            viewModel.hottestNews.observe(viewLifecycleOwner) { news ->
                if(news.isNotEmpty()){
                    setDataNews2(news)
                }
            }
            viewModel.findHottest()

            val layoutManager2 = LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
            binding!!.rvRecentNews.layoutManager = layoutManager2

            viewModel.recentNews.observe(viewLifecycleOwner) { news ->
                if (news.isNotEmpty()) {
                    setDataNews(news)
                }
            }
            viewModel.findRecent()
        }
    }

    private fun setDataNews2(newsList: List<DataItem>) {
        Log.d("NewsFragment", "setDataNews2 called with ${newsList.size} items")
        val adapter = NewsAdapterHottest()
        adapter.submitList(newsList)
        binding!!.rvHottestNews.adapter = adapter

        adapter.setOnItemClickCallback(object : NewsAdapterHottest.OnItemClickCallback{
            override fun onItemClicked(data: DataItem) {
                val bundle = Bundle()
                bundle.putString(EXTRA_ID, data.id)
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
        binding!!.rvRecentNews.adapter = adapter

        adapter.setOnItemClickCallBack(object : NewsAdapterRecent.OnItemClickCallback{
            override fun onItemClicked(data: DataItem) {
                val bundle = Bundle()
                bundle.putString(EXTRA_ID, data.id)
                NavHostFragment
                    .findNavController(this@NewsFragment)
                    .navigate(R.id.action_navigation_news_to_detailNewsFragment, bundle)
            }
        })
    }
}