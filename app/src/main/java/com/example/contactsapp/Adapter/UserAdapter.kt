package com.example.contactsapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.Model.User
import com.example.contactsapp.R

class UserAdapter(val User:MutableList<User>): RecyclerView.Adapter<UserViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindView(User[position])
    }

    override fun getItemCount(): Int {
        return User.size
    }

}


class UserViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){
    private val name: TextView = itemView.findViewById(R.id.tvName)

    fun bindView(User: User){
        name.text = User.name
    }
}