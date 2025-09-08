package com.akirachix.todos

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akirachix.todos.ui.theme.TodosTheme
import com.akirachix.todos.viewmodel.TodoAdapter
import com.akirachix.todos.viewmodel.TodoViewModel

class MainActivity : AppCompatActivity() {

  private lateinit var viewModel: TodoViewModel
  private lateinit var adapter: TodoAdapter
  private lateinit var recyclerView: RecyclerView
  private lateinit var progressBar: ProgressBar

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Initialize views
    recyclerView = findViewById(R.id.recyclerView)
    progressBar = findViewById(R.id.progressBar)

    // Setup RecyclerView
    recyclerView.layoutManager = LinearLayoutManager(this)
    adapter = TodoAdapter(emptyList())
    recyclerView.adapter = adapter

    // Initialize ViewModel
    viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

    // Observe LiveData
    viewModel.todos.observe(this, Observer { todos ->
      adapter.updateTodos(todos)
      progressBar.visibility = View.GONE
    })

    // Fetch todos
    progressBar.visibility = View.VISIBLE
    viewModel.fetchTodos()
  }
}

//package com.akirachix.todos
//
//import android.os.Bundle
//import android.view.View
//import android.widget.ProgressBar
//import androidx.appcompat.app.AppCompatActivity // Use this for View-based activities
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.get
//import androidx.lifecycle.observe
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.akirachix.todos.viewmodel.TodoAdapter // Assuming this is your RecyclerView Adapter
//import com.akirachix.todos.viewmodel.TodoViewModel
//
//class MainActivity : AppCompatActivity() {
//
//  private lateinit var viewModel: TodoViewModel
//  private lateinit var adapter: TodoAdapter
//  private lateinit var recyclerView: RecyclerView
//  private lateinit var progressBar: ProgressBar
//
//  override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_main)
//
//    // Initialize views
//    recyclerView = findViewById(R.id.recyclerView)
//    progressBar = findViewById(R.id.progressBar)
//
//    // Setup RecyclerView
//    recyclerView.layoutManager = LinearLayoutManager(this)
//    adapter = TodoAdapter(listOf()) // Initialize with an empty list
//    recyclerView.adapter = adapter
//
//    // Initialize ViewModel
//    viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
//
//    // Observe LiveData
//    viewModel.todos.observe(this, Observer { todos ->
//      adapter.updateTodos(todos) // Make sure your adapter has this method
//      progressBar.visibility = View.GONE
//    })
//
//    // Fetch todos
//    progressBar.visibility = View.VISIBLE
//    viewModel.fetchTodos()
//  }
//}
