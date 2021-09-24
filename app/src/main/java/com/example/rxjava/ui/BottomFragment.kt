package com.example.rxjava.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rxjava.util.Constants
import com.example.rxjava.databinding.FragmentBottomBinding

class BottomFragment: BaseFragment<FragmentBottomBinding>() {
    override val LOG_TAG = "BOTTOM_FRAGMENT"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBottomBinding = FragmentBottomBinding::inflate

    override fun setup() {
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindNewValueOnUi()
    }

    /**
     * this function will be excuted when the view is created, to bind the new data on ui
     * @param nothing
     * @return nothing
     * @author Wesam N. Shawqi
     */
    private fun bindNewValueOnUi() {
        arguments?.let {
            val result = it.getString(Constants.KEY_NAME)
            binding?.textView?.text =result
        }
    }

    companion object {
        /**
         * this function will return a new instance of bottom fragment with the new data bundled inside
         * @param data - the given data that we want to bundle
         * @return BottomFragment with the new data
         * @author Wesam N. Shawqi
         */
        fun createNewInstance(data:String): BottomFragment {
            return BottomFragment().apply {
                arguments=Bundle().apply {
                    putString(Constants.KEY_NAME,data)
                }
            }

        }
    }
}