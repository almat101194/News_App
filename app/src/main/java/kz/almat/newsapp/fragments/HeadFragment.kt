package kz.almat.newsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_head.*
import kz.almat.newsapp.MainActivity
import kz.almat.newsapp.R
import kz.almat.newsapp.fragments.adapter.NewsAdapter
import kz.almat.newsapp.util.Resource

class HeadFragment : Fragment(R.layout.fragment_head) {

    lateinit var viewModel: HeadViewModel
    lateinit var newsAdapter: NewsAdapter

    val TAG = "BreakingNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()
        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {response ->
             when(response ) {
                 is Resource.Success -> {
                     hideProgressBar()
                     response.data?.let {newsResponse ->
                         newsAdapter.differ.submitList(newsResponse.articles)
                     }

                 }
                 is Resource.Error -> {
                     hideProgressBar()
                     response.message?.let {message ->
                        Log.e(TAG, "An error occured: $message")
                     }
                 }
                 is Resource.Loading -> {
                     showProgressBar()
                 }
             }

        })
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }
}
