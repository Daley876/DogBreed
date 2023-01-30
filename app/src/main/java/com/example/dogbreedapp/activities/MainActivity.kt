package com.example.dogbreedapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dogbreedapp.R
import com.example.dogbreedapp.fragments.MainViewFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_layout)

        supportFragmentManager.beginTransaction()
            .add(R.id.main_content,MainViewFragment())
            .commit()

    }


}