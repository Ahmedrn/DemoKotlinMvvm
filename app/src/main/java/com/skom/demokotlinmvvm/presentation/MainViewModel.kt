package com.skom.demokotlinmvvm.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skom.demokotlinmvvm.domain.Article
import com.skom.demokotlinmvvm.domain.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getArticlesUseCase: GetArticlesUseCase) :
    ViewModel() {
    private val _articlesFlow: MutableStateFlow<List<Article>> = MutableStateFlow(emptyList())

    val articles: StateFlow<List<Article>> = _articlesFlow

    private val _spinner = MutableStateFlow<Boolean>(false)

    val spinner = _spinner.asStateFlow()

    fun getArticles() {
        _spinner.value = true
        viewModelScope.launch {
            getArticlesUseCase()
                .collect {
                    _spinner.value = false
                    _articlesFlow.value = it
                }
        }
    }

}