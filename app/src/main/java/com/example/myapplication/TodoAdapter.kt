package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemTodoBinding

class TodoAdapter(
    var todos: MutableList<Todo> = mutableListOf()
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var onDeleteClickListener: ((Todo) -> Unit)? = null

    inner class TodoViewHolder(val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: Todo) {
            binding.tvTitle.text = todo.title
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[position]

        holder.bind(todo)

        holder.binding.ivDltBtn.setOnClickListener {
            onDeleteClickListener?.invoke(todo)
        }
    }

    fun setOnDeleteClickListener(listener: (Todo) -> Unit) {
        onDeleteClickListener = listener
    }

    fun addTodo(todo: Todo) {
        todos.add(0, todo)
        notifyItemInserted(0)
    }

    fun removeTodo(todo: Todo) {
        val position = todos.indexOfFirst { it.id == todo.id }
        if (position != -1) {
            todos.removeAt(position)
            notifyItemRemoved(position)
        }
    }


    override fun getItemCount(): Int {
        return todos.size
    }
}