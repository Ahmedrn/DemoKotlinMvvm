package com.skom.demokotlinmvvm.presentation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.ProgressDialog.show
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.skom.demokotlinmvvm.R
import com.skom.demokotlinmvvm.databinding.ActivityMainBinding
import com.skom.demokotlinmvvm.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mViewModel: MainViewModel by viewModels()
    private val mAdapter = ArticlesAdapter()
    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initView()
        observeArticles()
    }

    override fun onStart() {
        super.onStart()
        handleNetworkChanges()
    }

    private fun initView() {
        mBinding.run {
            articlesRecyclerView.adapter = mAdapter
            swipeRefreshLayout.setOnRefreshListener {
                mViewModel.getArticles()
            }
        }
    }

    private fun observeArticles() {
        mViewModel.spinner.map { show ->
            mBinding.swipeRefreshLayout.isRefreshing = show
        }.launchIn(lifecycleScope)

        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                mViewModel.articles.collect { list ->
//                    try{
//                        mAdapter.submitList(list)
//                        mBinding.swipeRefreshLayout.isRefreshing = false
//
//                    }catch (e:Exception){
//                        e.printStackTrace()
//                        mBinding.swipeRefreshLayout.isRefreshing = false
//                    }
//                }
//            }

            mViewModel.articles.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { list ->
                    mAdapter.submitList(list)
                }
        }
    }

    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(applicationContext).observe(this) { isConnected ->
            if (!isConnected) {
                mBinding.textViewNetworkStatus.text =
                    getString(R.string.text_no_connectivity)
                mBinding.networkStatusLayout.apply {
                    visibility = View.VISIBLE
                    setBackgroundColor(getColor(R.color.colorStatusNotConnected))
                }
            } else {
                if (mAdapter.itemCount == 0) mViewModel.getArticles()
                mBinding.textViewNetworkStatus.text = getString(R.string.text_connectivity)
                mBinding.networkStatusLayout.apply {
                    setBackgroundColor(getColor(R.color.colorStatusConnected))

                    animate()
                        .alpha(1f)
                        .setStartDelay(ANIMATION_DURATION)
                        .setDuration(ANIMATION_DURATION)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                visibility = View.GONE
                            }
                        })
                }
            }
        }
    }


    companion object {
        const val ANIMATION_DURATION = 1000L
    }
}