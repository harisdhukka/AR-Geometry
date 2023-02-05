package com.example.myapplication.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.QuizAdapter
import com.example.myapplication.models.Quiz
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_quiz_menu.*

class QuizMenuActivity : AppCompatActivity() {
    lateinit var adapter: QuizAdapter
    private var quizList = mutableListOf<Quiz>()

    lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_menu)
        setUpFireStore()
        setUpRecyclerView()
    }

    private fun setUpFireStore(){
        firestore = FirebaseFirestore.getInstance()
        val collectionReference : CollectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener{value, error ->
            if(value == null || error != null){
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }
    }

    private fun setUpRecyclerView() {
        adapter = QuizAdapter(this, quizList)
        quizRecyclerView.layoutManager = GridLayoutManager(this, 2)
        quizRecyclerView.adapter = adapter
    }
}