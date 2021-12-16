package sk.sandeep.newsapp.presentation.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import sk.sandeep.newsapp.domain.use_case.GetNewsHeadlinesUseCase

class NewsViewModelFactory(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            app,
            getNewsHeadlinesUseCase
        ) as T
    }
}