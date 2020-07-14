package com.livedatademo.with_room_database.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.livedatademo.R
import com.livedatademo.with_room_database.entities.Word
import kotlinx.android.synthetic.main.item_view.view.*

class WordAdapter(private val list: LiveData<List<Word>>) : RecyclerView.Adapter<MyViewHolder>() {

    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (list.value == null) 0 else list.value!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.apply {
            word.text = list.value!![position].word
            comment.text = list.value!![position].anyComment
            ll_view.setOnClickListener {
                Toast.makeText(context, list.value!![position].anyComment, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


}
