package com.skom.demokotlinmvvm.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.skom.demokotlinmvvm.R
import com.skom.demokotlinmvvm.databinding.ItemArticleBinding
import com.skom.demokotlinmvvm.domain.Article

class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemArticleBinding.bind(view)

    fun bind(article: Article) {
        binding.postTitle.text = article.title
        binding.postAuthor.text = article.author
        binding.imageView.load(article.imageUrl) {
            placeholder(R.drawable.ic_photo)
            error(R.drawable.ic_broken_image)
        }
    }

}
