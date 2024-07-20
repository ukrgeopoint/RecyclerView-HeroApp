package com.example.listapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.listapp.data.Hero
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: RecyclerView = findViewById(R.id.listView)

        // Network request
        ApiClient.client.create(ApiInterface::class.java)
            .getHeroes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listView.adapter = RecyclerViewAdapter(it, onClick = {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                })
            }, {
                Toast.makeText(this, "Request error", Toast.LENGTH_SHORT).show()
            })

        listView.layoutManager = LinearLayoutManager(this)
        listView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
    }
}
