package com.samfonsec.hscards.presentation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samfonsec.hscards.R
import com.samfonsec.hscards.presentation.view.fragment.CardsListFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, CardsListFragment())
                .commit()
        }
    }
}
