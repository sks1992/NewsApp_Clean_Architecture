package sk.sandeep.newsapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import sk.sandeep.newsapp.R
import sk.sandeep.newsapp.data.util.Resource
import sk.sandeep.newsapp.databinding.FragmentNewsBinding
import sk.sandeep.newsapp.presentation.ui.activity.NewsActivity
import sk.sandeep.newsapp.presentation.ui.adapter.NewsAdapter
import sk.sandeep.newsapp.presentation.view_model.NewsViewModel


class NewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsFragmentBinding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter

    private var country = "in"
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsFragmentBinding = FragmentNewsBinding.bind(view)
        viewModel = (activity as NewsActivity).viewModel
        initRecyclerView()
        viewNewsList()
    }

    private fun viewNewsList() {
        viewModel.getNewsHeadLines(country, page)
        viewModel.newsHeadLines.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressbar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgressbar()
                    response.message?.let {
                        Toast.makeText(activity, "An Error Occurred", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressbar()
                }
            }
        })
    }

    private fun initRecyclerView() {
        newsAdapter = NewsAdapter()
        newsFragmentBinding.rvNews.adapter = newsAdapter
        newsFragmentBinding.rvNews.layoutManager = LinearLayoutManager(activity)
    }

    private fun showProgressbar() {
        newsFragmentBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressbar() {
        newsFragmentBinding.progressBar.visibility = View.INVISIBLE
    }
}