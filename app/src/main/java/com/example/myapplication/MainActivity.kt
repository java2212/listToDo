package com.example.myapplication

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter

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

        setupRecyclerView()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
       todoAdapter = TodoAdapter(mutableListOf())

        binding.rvToDo.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = todoAdapter
        }

        todoAdapter.setOnDeleteClickListener { todo ->
            todoAdapter.removeTodo(todo)
        }

    }

    private fun setupClickListeners() {
        binding.btnAdd.setOnClickListener {
            addNewTodo()
        }
        binding.etToDo.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                addNewTodo()
                true
            } else {
                false
            }
        }
    }

    private fun addNewTodo() {
        val todoText = binding.etToDo.text.toString().trim()

        if (todoText.isNotEmpty()) {
            val newTodo = Todo(title = todoText)
            todoAdapter.addTodo(newTodo)

            binding.etToDo.text.clear()
        }
    }



}