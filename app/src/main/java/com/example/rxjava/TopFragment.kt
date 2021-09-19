package com.example.rxjava

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.rxjava.databinding.FragmentTopBinding

class TopFragment:BaseFragment<FragmentTopBinding>() {
    override val LOG_TAG = "TOP_FRAGMENT"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTopBinding = FragmentTopBinding::inflate
    override fun addCallbacks() {

    }
}