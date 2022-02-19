package com.example.contactsapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.Model.User
import com.example.contactsapp.R

class UserAdapter(val User: MutableList<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    //private var userList: MutableList<User> = User


    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){
        private val name: TextView = itemView.findViewById(R.id.tvName)

        fun bindView(User: User){
                name.text = User.name
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            holder.bindView(User[position])


        /*  Testing purposes only
        holder.itemView.setOnClickListener(){
            Log.e("hmmMessage", "You pressed the user with name " + User.get(position).name.toString())
        } */
    }

    public override fun getItemCount(): Int {
        return User.size
    }

     fun removeItem(position: Int){
       // userList.removeAt(position)

       // notifyDataSetChanged()
       //  notifyItemChanged(position)
       //  notifyItemRemoved(position)
    }

    public fun setItems(item: MutableList<User>){
       // userList = item
       // notifyDataSetChanged()
    }


}

