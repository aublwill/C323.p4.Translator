package com.example.c323p4translator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.c323p4translator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityMainBinding
    //private lateinit var translationViewModel: TranslationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val translateFragment = TranslateFragment() // Create an instance of your TranslateFragment

// Replace the FrameLayout container with the TranslateFragment
        fragmentTransaction.replace(R.id.fWrite, translateFragment)
        fragmentTransaction.commit()
    }
    
}

