package com.example.dailynews.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dailynews.R
import com.example.dailynews.data.News
import com.example.dailynews.databinding.FragmentFavoritesNewsBinding
import com.example.dailynews.databinding.FragmentMainNewsBinding
import com.example.dailynews.utils.AppState
import com.example.dailynews.utils.Helper
import com.example.dailynews.utils.hide
import com.example.dailynews.utils.show
import com.example.dailynews.view.adapter.FavoritesNewsAdapter
import com.example.dailynews.viewModel.FavoritesNewsViewModel

class FavoritesNewsFragment: Fragment() {
    companion object {
        fun newInstance() = FavoritesNewsFragment()
    }

    private var binding: FragmentFavoritesNewsBinding? = null
    private val viewModel: FavoritesNewsViewModel by viewModels<FavoritesNewsViewModel>()
    private val adapter = FavoritesNewsAdapter()
    private lateinit var web: WebView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesNewsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            web = wvMain
            web.hide()

            rvFavoritesList.adapter = adapter

            rvFavoritesList.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            ivMainList.setOnClickListener {
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.add(R.id.container, MainNewsFragment.newInstance())
                    ?.addToBackStack("")
                    ?.commitAllowingStateLoss()
            }
        }

        adapter.apply {
            setOnItemClickListener {
                openNewsUrl(it.url)
            }

            setOnShareClickListener {
                shareNews(it)
            }
        }

        viewModel.newsData.observe(viewLifecycleOwner) { observeNewsData(it) }
        viewModel.getFavoritesNews()
    }

    private fun openNewsUrl(url: String){
        web.show()
        web.webViewClient = WebViewClient()
        web.apply {
            loadUrl(url)
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }
    }

    private fun shareNews(news: News){
        Helper.copyToClipBoard(news.url)
    }

    private fun observeNewsData(data: AppState) {
        when (data) {
            is AppState.Loading -> {
                binding?.pbMain?.show()
            }
            is AppState.Success -> {
                binding?.pbMain?.hide()
                adapter.setData(data.data)
            }
        }
    }
}