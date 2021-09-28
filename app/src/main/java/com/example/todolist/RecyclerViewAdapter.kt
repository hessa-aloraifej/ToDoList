package com.example.todolist


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*


class RecyclerViewAdapter(private val todos: ArrayList<ToDo>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
         val todo=todos[position]

        holder.itemView.apply {
            textView2.text=todo.title
            checkBox.isChecked=todo.status
            checkBox.setOnClickListener {
                if (todo.status){
                    textView2.setTextColor(Color.GRAY)
                }
                textView2.setTextColor(Color.BLACK)
                todo.status=!todo.status
            }

        }

    }

    override fun getItemCount() = todos.size

}

