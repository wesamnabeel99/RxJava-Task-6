package com.example.rxjava

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.rxjava.databinding.FragmentBottomBinding

class BottomFragment:BaseFragment<FragmentBottomBinding>() {
    override val LOG_TAG = "BOTTOM_FRAGMENT"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBottomBinding = FragmentBottomBinding::inflate

    override fun setup() {
        }

    override fun onStart() {
        super.onStart()
        arguments?.let {
            val result = it.getString(Constants.KEY_NAME)
            binding?.textView?.text =result
        }
    }
    companion object {
        fun newInstance(name:String):BottomFragment {

            return BottomFragment().apply {
                arguments=Bundle().apply {
                    putString(Constants.KEY_NAME,name)
                }
            }

        }
    }
}