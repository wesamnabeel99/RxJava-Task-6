package com.example.rxjava

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.rxjava.databinding.ActivityMainBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val topFragment = TopFragment()
    private val bottomFragment = BottomFragment()
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup() {
        addFragment(topFragment,R.id.fragment_container)
        addFragment(bottomFragment,R.id.fragment_container_two)

    }

    private fun addFragment(fragment: Fragment,id:Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(id,fragment)
        transaction.commit()
    }

    companion object {
        const val LOG_TAG ="MAIN_ACTIVITY"
    }
}