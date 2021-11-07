package com.sumanta.flowapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sumanta.flowapi.adapter.PostAdapter
import com.sumanta.flowapi.model.Post
import com.sumanta.flowapi.viewmodel.PortViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private lateinit var postViewModel: PortViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
        postViewModel = ViewModelProvider(this)[PortViewModel::class.java]
        postViewModel.getPost()
        postViewModel.postLiveData.observe(this, Observer {
            postAdapter.setPostData(it as ArrayList<Post>)
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        })
    }

    private fun initUi() {
        recyclerView = findViewById(R.id.recyclerView)
        postAdapter = PostAdapter(this, ArrayList())
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
    }
}