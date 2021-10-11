package com.azad.mvvmproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.azad.mvvmproject.R
import com.azad.mvvmproject.model.Blog
import com.azad.mvvmproject.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Error
import java.lang.StringBuilder

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug";

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)
    }

    private fun subscribeObservers(){
       viewModel.dataState.observe(this, Observer { dataState ->
           when(dataState){
               is DataState.Success<List<Blog>> -> {
                   displayProgressBar(false)
                   appendBlogTitles(dataState.data)
               }
               is DataState.Error -> {
                   displayProgressBar(false)
                   displayError(dataState.exception.message)
               }
               is DataState.Loading -> {
                   displayProgressBar(true)
               }
           }
       })
    }

    private fun displayError(message: String?){
        if (message != null){
            textView.text = message
        }else{
            textView.text = "Unknown error!"
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean){
        progressBar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    private fun appendBlogTitles(blogs: List<Blog>){
        var sb = StringBuilder()
        for (blog in blogs){
            sb.append(blog.title + "\n")
        }
        textView.text = sb.toString()
    }
}