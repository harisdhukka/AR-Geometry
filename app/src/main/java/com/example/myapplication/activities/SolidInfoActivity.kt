package com.example.myapplication.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.models.SolidInfo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_solid_info.*

class SolidInfoActivity : AppCompatActivity() {
    private lateinit var firestore: FirebaseFirestore

    var solidInfoList : MutableList<SolidInfo>? = null
    var information: SolidInfo? = null
    var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solid_info)
        setUpFireStore()
        setUpEventListener()
    }

    private fun setUpFireStore() {
        firestore = FirebaseFirestore.getInstance()
        title = intent.getStringExtra("name")
        if (title != null) {
            firestore.collection("solids").whereEqualTo("title", title)
                .get()
                .addOnSuccessListener {
                    if (it != null && !it.isEmpty) {
                        solidInfoList = it.toObjects(SolidInfo::class.java)
                        information = solidInfoList!![0]
                        bindViews()
                    }
                }
        }
    }

    private fun setUpEventListener() {
        loadSolid.setOnClickListener {
            val intent = Intent(this, ArActivity::class.java)
            intent.putExtra("name", title)
            startActivity(intent)
        }
    }

    private fun bindViews() {
        information?.let {
            solidImage.setImageResource(resources.getIdentifier(it.title+"info", "drawable", packageName))
            solid_name.text = it.title.toUpperCase()
            textView.text = "Number of Edges: " + it.numberOfEdges
            textView2.text = "Number of Faces: " + it.numberOfFaces
            textView3.text = "Number of Vertices: " + it.numberOfVertices
            textView4.text = "Volume: " + it.volume
            textView5.text = "Surface Area: " + it.surfaceArea
        }
    }
}


