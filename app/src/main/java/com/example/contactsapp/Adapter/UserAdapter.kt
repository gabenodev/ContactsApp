package com.example.contactsapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.Model.User
import com.example.contactsapp.R
import java.util.*
import kotlin.random.Random


class UserAdapter(val User: MutableList<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    //private var userList: MutableList<User> = User
    val avatarList = mutableListOf<Int>(R.drawable.profile_icon,R.drawable.profile_icon2,R.drawable.profile_icon3,R.drawable.profile_icon4,R.drawable.profile_icon5,R.drawable.initials_bg)

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){
        private val name: TextView = itemView.findViewById(R.id.tvName)
        private val profileIcon: ImageView = itemView.findViewById(R.id.profileIcon)
        private val firstInitial: TextView = itemView.findViewById(R.id.firstInitial)
        private val secondIntial: TextView = itemView.findViewById(R.id.secondInitial)

        fun bindView(User: User){
                name.text = User.name
            if(User.id!! % 2 == 1){
                profileIcon.setImageResource(avatarList[Random.nextInt(0,avatarList.size-1)])

            } else {
                profileIcon.setImageResource(avatarList[5])
                if(User.name?.contains(".")==false) {
                    firstInitial.text = User.name?.subSequence(0, 1)
                    secondIntial.text = User.name?.get(spaceIndex(User.name) + 1).toString()
                } else {
                    firstInitial.text = User.name?.get(spaceIndex(User.name)+1).toString()
                    secondIntial.text = User.name?.get(secondSpaceIndex(User.name)+1).toString()
                }

            }

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

    fun spaceIndex(name: String): Int{
        var result : Int = 0
        for(index in 0..name.length-1){
            if(name[index].isWhitespace()){
                result = index
                break;
            }
        }
        return result
    }

    fun secondSpaceIndex(name:String): Int{
        var result : Int = 0
        var countSpace: Int = 0;
        for(index in 0..name.length-1){
            if(name[index].isWhitespace()){
                countSpace++
                if(countSpace == 2){
                    result = index
                    break;
                }
            }
        }
        return result
    }


}

