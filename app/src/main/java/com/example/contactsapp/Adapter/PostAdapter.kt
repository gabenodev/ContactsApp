package com.example.contactsapp.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.Model.Post
import com.example.contactsapp.R

class PostAdapter (val Post: MutableList<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>(){

    //private val noPost: String = "No posts"

    //Inner Class
    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){
        private val postTitle: TextView = itemView.findViewById(R.id.postTitle)
        private val postBody: TextView = itemView.findViewById(R.id.postBody)

        fun bindView(Post: Post){

                postTitle.text = Post.title
                postBody.text = Post.body
                Log.e("pf...", itemCount.toString())

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindView(Post[position])
    }

    override fun getItemCount(): Int {
        return Post.size
    }
}