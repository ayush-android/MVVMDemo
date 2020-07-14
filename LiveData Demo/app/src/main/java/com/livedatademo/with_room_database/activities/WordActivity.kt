package com.livedatademo.with_room_database.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.livedatademo.R
import com.livedatademo.with_room_database.adapter.WordAdapter
import com.livedatademo.with_room_database.viewmodel.WordActivityViewModel
import kotlinx.android.synthetic.main.activity_word.*

class WordActivity : AppCompatActivity() {

    private val viewModel: WordActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word)

        recycle_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = WordAdapter(viewModel.wordList)
        recycle_view.adapter = adapter

        viewModel.wordList.observe(this, Observer {
            Log.e("Tag", "observer called " + it.size)
            adapter.notifyDataSetChanged()
        })

        floating_btn.setOnClickListener {
            viewModel.insertWord()
        }

        viewModel.getCommentForWord("Ayush").observe(this, Observer {
            Log.e("Tag", "observer for comment called ")
            if (it != null)
                Toast.makeText(this, it.anyComment, Toast.LENGTH_SHORT).show()
        })
    }
}