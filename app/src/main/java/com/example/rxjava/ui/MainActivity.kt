package com.example.rxjava.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.rxjava.util.Messenger
import com.example.rxjava.R
import com.example.rxjava.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , Messenger {
    private val topFragment = TopFragment(this)
    private val bottomFragment = BottomFragment()
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup() {
        addFragment(topFragment, R.id.fragment_container)
        addFragment(bottomFragment, R.id.fragment_container_two)
    }

     private fun addFragment(fragment: Fragment,id:Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(id,fragment)
        transaction.commit()
    }
    private fun replaceFragment(fragment: Fragment , id:Int) {
        val transition = supportFragmentManager.beginTransaction()
        transition.replace(id,fragment).commit()
    }
    companion object {
        const val LOG_TAG ="MAIN_ACTIVITY"
    }

    override fun sendData(data: String) {
        replaceFragment(BottomFragment.createNewInstance(data), R.id.fragment_container_two)
    }
}