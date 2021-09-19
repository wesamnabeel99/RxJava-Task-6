package com.example.rxjava

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.rxjava.databinding.FragmentBottomBinding

class BottomFragment:BaseFragment<FragmentBottomBinding>() {
    override val LOG_TAG = "BOTTOM_FRAGMENT"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBottomBinding = FragmentBottomBinding::inflate

    override fun addCallbacks() {
    }
}