package com.example.recyclerviewswipedemoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewswipedemoapp.CourseAdapter.CourseViewHolder
import com.example.recyclerviewswipedemoapp.models.Course

class CourseAdapter(
    private val courseList: ArrayList<Course>,
    private val context: Context
) : RecyclerView.Adapter<CourseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.course_list_item,
            parent, false
        )
        return CourseViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.courseName.text = courseList.get(position).courseName
        holder.courseImage.setImageResource(courseList.get(position).courseImg)
    }


    override fun getItemCount(): Int {
        return courseList.size
    }

    class CourseViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseName: TextView = itemView.findViewById(R.id.courseName)
        val courseImage: ImageView = itemView.findViewById(R.id.courseImage)
    }
}