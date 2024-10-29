package com.myproject.searchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class StudentAdapter(private var students: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(val cardView: MaterialCardView) : RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false) as MaterialCardView
        return StudentViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.cardView.findViewById<TextView>(R.id.nameTextView).text = student.name
        holder.cardView.findViewById<TextView>(R.id.studentIdTextView).text = student.studentId
    }

    override fun getItemCount() = students.size

    fun updateList(newList: List<Student>) {
        students = newList
        notifyDataSetChanged()
    }
}