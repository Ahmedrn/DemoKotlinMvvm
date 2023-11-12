package com.skom.demokotlinmvvm.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.skom.demokotlinmvvm.R
import com.skom.demokotlinmvvm.domain.Article

class ArticlesAdapter() :
    ListAdapter<Article, ArticleViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem
        }
    }
}