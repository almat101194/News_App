package kz.almat.newsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kz.almat.newsapp.MainActivity
import kz.almat.newsapp.R

class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: HeadViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

}