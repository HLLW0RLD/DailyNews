package com.example.dailynews.view

import android.annotation.SuppressLint
import android.content.*
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
import com.example.dailynews.data.Categories
import com.example.dailynews.data.News
import com.example.dailynews.databinding.FragmentMainNewsBinding
import com.example.dailynews.utils.AppState
import com.example.dailynews.utils.Helper
import com.example.dailynews.utils.hide
import com.example.dailynews.utils.show
import com.example.dailynews.view.adapter.MainNewsAdapter
import com.example.dailynews.viewModel.MainNewsViewModel
import org.koin.core.component.KoinComponent

class MainNewsFragment : Fragment(), KoinComponent {
    companion object {
        fun newInstance() = MainNewsFragment()
    }

    private var binding: FragmentMainNewsBinding? = null
    private val viewModel: MainNewsViewModel by viewModels<MainNewsViewModel>()
    private val adapter = MainNewsAdapter()
    private lateinit var web: WebView

    private lateinit var searchText: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainNewsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            icgCategories.ccAll.isChecked = true
            web = wvMain
            web.hide()

            rvNewsList.adapter = adapter

            rvNewsList.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            icgCategories.cgCategories.setOnCheckedChangeListener { _, checkedId ->
                setChipAction(checkedId)
            }

            ivSearch.setOnClickListener {
                searchText = etSearch.text.toString()
                Helper.hideKeyboard(this@MainNewsFragment)
                if (searchText.isNotEmpty()) {
                    viewModel.searchNews(searchText)
                } else {
                    Helper.toastShort("Search field is empty")
                }
            }

            ivFavorites.setOnClickListener {
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.add(R.id.container, FavoritesNewsFragment.newInstance())
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

            setOnSaveClickListener {
                saveToFavorites(it)
            }
        }

        viewModel.newsData.observe(viewLifecycleOwner) { observeNewsData(it) }
        viewModel.getAllNews()
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

    private fun saveToFavorites(news: News){
        viewModel.saveToFavorites(news)
    }

    private fun setChipAction(id: Int) {
        when (id) {
            R.id.cc_all -> {
                viewModel.getAllNews()
            }
            R.id.cc_business -> {
                viewModel.getNewsByCategory(Categories.BUSINESS)
            }
            R.id.cc_entertainment -> {
                viewModel.getNewsByCategory(Categories.ENTERTAINMENT)
            }
            R.id.cc_general -> {
                viewModel.getNewsByCategory(Categories.GENERAL)
            }
            R.id.cc_health -> {
                viewModel.getNewsByCategory(Categories.HEALTH)
            }
            R.id.cc_science -> {
                viewModel.getNewsByCategory(Categories.SCIENCE)
            }
            R.id.cc_sports -> {
                viewModel.getNewsByCategory(Categories.SPORTS)
            }
            R.id.cc_technology -> {
                viewModel.getNewsByCategory(Categories.TECHNOLOGY)
            }
        }
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