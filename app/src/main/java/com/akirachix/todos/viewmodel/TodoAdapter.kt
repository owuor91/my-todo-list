package com.akirachix.todos.viewmodel

import android.view.LayoutInflater
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akirachix.todos.model.Todo
import android.view.View
import android.view.ViewGroup
import com.akirachix.todos.R

class TodoAdapter(private var todos: List<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvUserId: TextView = itemView.findViewById(R.id.tvUserId)
        val tvCompleted: TextView = itemView.findViewById(R.id.tvCompleted)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[position]
        holder.tvTitle.text = todo.title
        holder.tvUserId.text = "User ID: ${todo.userId}"
        holder.tvCompleted.text = "Completed: ${if (todo.completed) "Yes" else "No"}"
    }

    override fun getItemCount() = todos.size

    fun updateTodos(newTodos: List<Todo>) {
        todos = newTodos
        notifyDataSetChanged()
    }
}

