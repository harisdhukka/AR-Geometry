package com.example.myapplication.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.OptionAdapter
import com.example.myapplication.models.Question
import com.example.myapplication.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {
    lateinit var optionAdapter: OptionAdapter
    var quizzes : MutableList<Quiz>? = null
    var questions: MutableMap<String, Question>? = null
    var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        setUpFirestore()
        setUpEventListener()
    }

    private fun setUpEventListener() {
        btnPrevious.setOnClickListener {
            index--
            bindViews()
        }

        btnNext.setOnClickListener {
            index++
            bindViews()
        }

        btnSubmit.setOnClickListener {
            val intent = Intent(this, QuizResultActivity::class.java)
            val json  = Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ", json)
            startActivity(intent)
            finish()
        }
    }

    private fun setUpFirestore() {
        val firestore = FirebaseFirestore.getInstance()
        val title = intent.getStringExtra("TITLE")
        if (title != null) {
            firestore.collection("quizzes").whereEqualTo("title", title)
                .get()
                .addOnSuccessListener {
                    if(it != null && !it.isEmpty){
                        quizzes = it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].questions
                        bindViews()
                    }
                }
        }
    }

    private fun bindViews() {
        btnPrevious.visibility = View.GONE
        btnSubmit.visibility = View.GONE
        btnNext.visibility = View.GONE

        when (index) {
            1 -> { //first question
                btnNext.visibility = View.VISIBLE
            }
            questions!!.size -> { // last question
                btnSubmit.visibility = View.VISIBLE
                btnPrevious.visibility = View.VISIBLE
            }
            else -> { // middle questions
                btnPrevious.visibility = View.VISIBLE
                btnNext.visibility = View.VISIBLE
            }
        }

        val question = questions!!["question$index"]

        question?.let {
            description.text = it.description
            optionAdapter = OptionAdapter(this, it)
            optionList.layoutManager = LinearLayoutManager(this)
            optionList.adapter = optionAdapter
            optionList.setHasFixedSize(true)
        }
    }
}
