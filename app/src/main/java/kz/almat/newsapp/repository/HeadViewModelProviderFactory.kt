package kz.almat.newsapp.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kz.almat.newsapp.fragments.HeadViewModel

class HeadViewModelProviderFactory (
    val headRepository: HeadRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HeadViewModel(headRepository) as T
    }
}