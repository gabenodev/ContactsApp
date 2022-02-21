package com.example.contactsapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.Model.User
import com.example.contactsapp.R
import kotlin.random.Random


class UserAdapter(val User: MutableList<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    //private var userList: MutableList<User> = User
    val avatarList = mutableListOf<Int>(R.drawable.profile_icon,R.drawable.profile_icon2,R.drawable.profile_icon3,R.drawable.profile_icon4,R.drawable.profile_icon5,R.drawable.initials_bg)

    // Interface for item click listener for RecyclerView...

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{

        fun onItemClick(index: Int)

    }

    fun setOnItemClickListener(listener: onItemClickListener){

        mListener = listener

    }


    // Inner class UserViewHODLer

    inner class UserViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder (itemView){
        private val name: TextView = itemView.findViewById(R.id.tvName)
        private val profileIcon: ImageView = itemView.findViewById(R.id.profileIcon)
        private val firstInitial: TextView = itemView.findViewById(R.id.firstInitial)
        private val secondIntial: TextView = itemView.findViewById(R.id.secondInitial)

        init {

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

        }

        fun bindView(User: User){
                name.text = User.name

            if(User.id!! % 2 == 1){
                profileIcon.setImageResource(User.image!!)

            } else {
                profileIcon.setImageResource(avatarList[5])

                if(User.name?.contains(".")== false) {

                    firstInitial.text = User.name.subSequence(0, 1)
                    secondIntial.text = User.name[spaceIndex(User.name) + 1].toString()

                } else {
                    firstInitial.text = User.name!![spaceIndex(User.name)+1].toString()
                    secondIntial.text = User.name[secondSpaceIndex(User.name)+1].toString()
                }



            }

        }

    }


    // Adapter basic functions
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false)
        return UserViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            holder.bindView(User[position])

        /* In java it's easier to do it... :(
        This is how I would have done it in Java :~)
        //  Testing purposes only
        holder.itemView.setOnClickListener(){
          // Log.e("hmmMessage", "You pressed the user with name " + User.get(position).name.toString())

        }

         */
    }

    override fun getItemCount(): Int {
        return User.size
    }


    // THIS FUNCTION ACTUALLY SAVED MY LIFE WITH THE DAMN VISUAL BUG...
    override fun getItemViewType(position: Int): Int {
        return position
    }

/*
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
*/

    fun spaceIndex(name: String): Int{
        var result : Int = 0
        for(index in name.indices){
            if(name[index].isWhitespace()){
                result = index
                break;
            }
        }
        return result
    }

    fun secondSpaceIndex(name:String): Int{
        var result : Int = 0
        var countSpace: Int = 0
        for(index in name.indices){
            if(name[index].isWhitespace()){
                countSpace++
                if(countSpace == 2){
                    result = index
                    break
                }
            }
        }
        return result
    }


}

