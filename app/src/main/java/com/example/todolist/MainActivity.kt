package com.example.todolist

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var messages: ArrayList<ToDo>
    lateinit var myrv:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messages = arrayListOf()
        var b = findViewById<FloatingActionButton>(R.id.buttun)
         myrv = findViewById<RecyclerView>(R.id.rvmain)


        b.setOnClickListener {
            customAlert()
            myrv.adapter = RecyclerViewAdapter(messages)
            myrv.layoutManager = LinearLayoutManager(this)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu -> {
                var removed= arrayListOf<ToDo>()
                 for(i in messages){
                     if(i.status)
                     { removed.add(i)
                     }
                 }
                messages.removeAll(removed)
                myrv.adapter?.notifyDataSetChanged()

                return true
            }


        }
        return super.onOptionsItemSelected(item)
    }

    private fun customAlert() {

        val dialogBuilder = AlertDialog.Builder(this)

        val input = EditText(this)

        dialogBuilder.setMessage("Enter your List")

            .setPositiveButton("Submit",
                DialogInterface.OnClickListener { dialog, id ->
                    messages.add(ToDo(input.text.toString(), false))
                })

            .setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })


        val alert = dialogBuilder.create()

        alert.setTitle("New list")
        alert.setView(input)
        alert.show()
    }


}

