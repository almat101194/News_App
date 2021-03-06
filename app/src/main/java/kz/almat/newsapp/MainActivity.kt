package kz.almat.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import kz.almat.newsapp.db.ArticleDatabase
import kz.almat.newsapp.fragments.HeadViewModel
import kz.almat.newsapp.repository.HeadRepository
import kz.almat.newsapp.repository.HeadViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: HeadViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Start to Write

        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())

        val headRepository = HeadRepository(ArticleDatabase(this))
        val viewModelProviderFactory = HeadViewModelProviderFactory(headRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(HeadViewModel::class.java)

    }


}