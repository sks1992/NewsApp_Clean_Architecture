package sk.sandeep.newsapp.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import sk.sandeep.newsapp.R
import sk.sandeep.newsapp.databinding.ActivityNewsBinding
import sk.sandeep.newsapp.presentation.ui.adapter.NewsAdapter
import sk.sandeep.newsapp.presentation.view_model.NewsViewModel
import sk.sandeep.newsapp.presentation.view_model.NewsViewModelFactory
import javax.inject.Inject

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    private lateinit var navController: NavController

    lateinit var viewModel: NewsViewModel
    @Inject
    lateinit var factory: NewsViewModelFactory

    @Inject
    lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNaviagtion.setupWithNavController(navController)


        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]
    }
}

/**
 * A ->Presentation
 * B ->Domain
 * C ->Data
A        A             B         BC              C                    C
view -> viewModel -> UseCase -> Repository ->1.Local DataSource 2.Remote DataSource

View Has References Of ViewModel
ViewModel Has References Of UseCase
UseCase Has References Of Repository
Repository Has References Of 1.Local DataSource 2.Remote DataSource
 * */