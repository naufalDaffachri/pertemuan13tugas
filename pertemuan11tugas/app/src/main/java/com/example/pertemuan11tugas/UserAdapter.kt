package com.example.pertemuan11tugas.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.pertemuan11tugas.R
import com.example.pertemuan11tugas.model.Data
import com.example.pertemuan11tugas.ui.DetailActivity

class UserAdapter(private val userList: List<Data>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvEmail: TextView = view.findViewById(R.id.tvEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.tvName.text = "${user.firstName} ${user.lastName}"
        holder.tvEmail.text = user.email

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("EXTRA_NAME", "${user.firstName} ${user.lastName}")
            intent.putExtra("EXTRA_EMAIL", user.email)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = userList.size
}
