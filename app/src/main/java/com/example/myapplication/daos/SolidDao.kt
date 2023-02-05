package com.example.myapplication.daos

import com.example.myapplication.R
import com.example.myapplication.models.Solid

class SolidDao {
    fun loadSolid(): MutableList<Solid> {
        return mutableListOf<Solid>(
            Solid ("cone", R.drawable.cone),
            Solid("cube", R.drawable.cube),
            Solid("cuboid", R.drawable.cuboid),
            Solid("cylinder", R.drawable.cylinder),
            Solid("sphere", R.drawable.sphere)
        )
    }
}