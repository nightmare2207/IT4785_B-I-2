package com.myproject.searchlist

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var adapter: StudentAdapter
    private val studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        // Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter(studentList)
        recyclerView.adapter = adapter

        // Add sample data
        addSampleData()

        // Setup search functionality
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterStudents(newText)
                return true
            }
        })
    }

    private fun filterStudents(query: String?) {
        if (query == null || query.length < 2) {
            adapter.updateList(studentList)
            return
        }

        val filteredList = studentList.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.studentId.contains(query, ignoreCase = true)
        }
        adapter.updateList(filteredList)
    }

    private fun addSampleData() {
        studentList.addAll(listOf(
            Student("Nguyen Van A", "SV001"),
            Student("Tran Thi B", "SV002"),
            Student("Le Van C", "SV003"),
            Student("Pham Thi D", "SV004")
        ))
        adapter.notifyDataSetChanged()
    }
}