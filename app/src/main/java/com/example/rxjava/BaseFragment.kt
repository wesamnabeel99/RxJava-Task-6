package com.example.rxjava

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseFragment<VB: ViewBinding> : Fragment() {


    //region initialize variables
    val compositeDisposable  = CompositeDisposable()
    abstract val LOG_TAG : String
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean ) -> VB
    private var _binding: ViewBinding? = null
    protected val binding
        get() = _binding as VB?
    //endregion

    //region fragment creation override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        _binding = bindingInflater(inflater,container,false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup()
    }
    //endregion

    //region abstract functions
    abstract fun setup()
    //endregion

    protected fun log(value: String){
        Log.v(LOG_TAG,value)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }


}