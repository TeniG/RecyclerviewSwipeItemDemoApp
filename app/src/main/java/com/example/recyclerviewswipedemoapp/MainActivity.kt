package com.example.recyclerviewswipedemoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewswipedemoapp.models.Course
import com.google.android.material.snackbar.Snackbar
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    lateinit var courseRecyclerView: RecyclerView
    lateinit var courseAdapter: CourseAdapter
    lateinit var courseList: ArrayList<Course>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        courseRecyclerView = findViewById(R.id.courseRecyclerView)

        courseList = ArrayList()

        courseAdapter = CourseAdapter(courseList, this)
        courseRecyclerView.adapter = courseAdapter

        // on below line we are adding data to our list
        courseList.add(Course("Android Development", androidx.core.R.drawable.ic_call_answer))
        courseList.add(Course("C++ Development", androidx.core.R.drawable.notification_bg_low_pressed))
        courseList.add(Course("Java Development", androidx.core.R.drawable.ic_call_decline_low))
        courseList.add(Course("Python Development", androidx.core.R.drawable.ic_call_answer_video))
        courseList.add(Course("JavaScript Development", com.google.android.material.R.drawable.ic_mtrl_chip_checked_circle))

        // on below line we are notifying adapter
        // that data has been updated.
        courseAdapter.notifyDataSetChanged()


        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN,ItemTouchHelper.LEFT or  ItemTouchHelper.DOWN){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                Log.d("MainActivity", "onMove: ")
                return false
            }

            override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
                return 0.3f
//                return super.getSwipeThreshold(viewHolder)
            }


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                Log.d("MainActivity", "onSwiped: direction: $direction")
                val deletedCourse: Course = courseList.get(viewHolder.adapterPosition)

                // below line is to get the position
                // of the item at that position.
                val position = viewHolder.adapterPosition

                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                courseList.removeAt(viewHolder.adapterPosition)

                // below line is to notify our item is removed from adapter.
                courseAdapter.notifyItemRemoved(viewHolder.adapterPosition)

                // below line is to display our snackbar with action.
                // below line is to display our snackbar with action.
                // below line is to display our snackbar with action.
                Snackbar.make(courseRecyclerView, "Deleted " + deletedCourse.courseName, Snackbar.LENGTH_LONG)
                    .setAction(
                        "Undo",
                        View.OnClickListener {
                            // adding on click listener to our action of snack bar.
                            // below line is to add our item to array list with a position.
                            courseList.add(position, deletedCourse)

                            // below line is to notify item is
                            // added to our adapter class.
                            courseAdapter.notifyItemInserted(position)
                        }).show()
            }

        }).attachToRecyclerView(courseRecyclerView)
    }
}