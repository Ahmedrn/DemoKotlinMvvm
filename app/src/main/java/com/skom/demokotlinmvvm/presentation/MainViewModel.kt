package com.skom.demokotlinmvvm.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skom.demokotlinmvvm.domain.Article
import com.skom.demokotlinmvvm.domain.ArticleRepository
import com.skom.demokotlinmvvm.domain.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getArticlesUseCase: GetArticlesUseCase) :
    ViewModel() {
    private val _articlesFlow: MutableStateFlow<List<Article>> = MutableStateFlow(emptyList())

    val articles: StateFlow<List<Article>> = _articlesFlow

    fun getArticles() {
        viewModelScope.launch {
            getArticlesUseCase()
                .collect {
                    _articlesFlow.value = it
                }
        }
    }

}