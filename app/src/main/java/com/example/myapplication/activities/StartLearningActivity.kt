package com.example.myapplication.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.SolidItemAdapter
import com.example.myapplication.daos.SolidDao
import com.example.myapplication.models.Solid
import kotlinx.android.synthetic.main.activity_start_learning.*

class StartLearningActivity : AppCompatActivity() {
    lateinit var adapter: SolidItemAdapter
    private var myDataset = mutableListOf<Solid>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_learning)
        loadData()
        setUpRecyclerView()
    }

    private fun loadData(){
        myDataset = SolidDao().loadSolid()
    }

    private fun setUpRecyclerView() {
        adapter = SolidItemAdapter(this, myDataset)
        solidsMenuView.layoutManager = GridLayoutManager(this, 2)
        solidsMenuView.adapter = adapter
        solidsMenuView.setHasFixedSize(true)
        adapter.onItemClick = {
            Solid ->
            val intent = Intent(this, SolidInfoActivity::class.java)
            intent.putExtra("name", Solid.title)
            startActivity(intent)
        }
    }
}