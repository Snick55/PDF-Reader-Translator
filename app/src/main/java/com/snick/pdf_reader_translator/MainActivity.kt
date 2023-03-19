package com.snick.pdf_reader_translator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.snick.pdf_reader_translator.presentation.FilesFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null){
            val fragment = FilesFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
        }

    }
}