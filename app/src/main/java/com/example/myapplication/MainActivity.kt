package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val todos = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, todos)
        binding.lvToDo.adapter = adapter

        binding.btnAdd.setOnClickListener {
            addToDo()
        }

        binding.lvToDo.setOnItemLongClickListener { parent, view, position, id ->
            deleteTodo(position)
            true // возвращаем true, чтобы показать что событие обработано
        }
    }

    private fun addToDo() {
        val text = binding.etToDo.text.toString().trim()
        if (text.isNotEmpty()) {
            todos.add(text)
            adapter.notifyDataSetChanged()
            binding.etToDo.text.clear()
        }
    }

    private fun deleteTodo(position: Int) {
        if (position >= 0 && position < todos.size) {
            val deletedTodo = todos.removeAt(position)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Удалено: $deletedTodo", Toast.LENGTH_SHORT).show()
        }
    }
}