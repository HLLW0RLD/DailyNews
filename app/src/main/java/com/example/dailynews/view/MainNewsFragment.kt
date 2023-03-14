package com.example.dailynews.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dailynews.databinding.FragmentMainNewsBinding
import com.example.dailynews.utils.AppState
import com.example.dailynews.utils.hide
import com.example.dailynews.utils.show
import com.example.dailynews.view.adapter.MainNewsAdapter
import com.example.dailynews.viewModel.MainNewsViewModel
import org.koin.core.component.KoinComponent

class MainNewsFragment: Fragment(), KoinComponent {
    companion object {
        fun newInstance() = MainNewsFragment()
    }

    private var binding: FragmentMainNewsBinding? = null
    private val viewModel: MainNewsViewModel by viewModels<MainNewsViewModel>()
    private val adapter = MainNewsAdapter()

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
            rvNewsList.adapter = adapter

            rvNewsList.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        viewModel.newsData.observe(viewLifecycleOwner) { observNewsData(it) }
        viewModel.getAllNews()
    }

    private fun observNewsData(data: AppState) {
        when (data) {
            is AppState.Loading ->{
                binding?.pbMain?.show()
            }
            is AppState.Success -> {
                binding?.pbMain?.hide()
                adapter.setData(data.data.articles)
            }
        }
    }
}