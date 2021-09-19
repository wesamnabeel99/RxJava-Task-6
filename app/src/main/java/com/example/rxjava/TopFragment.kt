package com.example.rxjava

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.example.rxjava.databinding.FragmentTopBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class TopFragment: BaseFragment<FragmentTopBinding>() {
    override val LOG_TAG = "TOP_FRAGMENT"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTopBinding = FragmentTopBinding::inflate
    override fun setup() {
        observeDataOnEditText()
    }

    private fun observeDataOnEditText() {
        val observable = Observable.create<String> {emitter ->
            binding?.editText?.doOnTextChanged { text, start, before, count ->
                emitter.onNext(text.toString())
            }
        }.debounce(1500,TimeUnit.MILLISECONDS)
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ t->
            bindTheText(t)
        }, {e ->
            Log.i(LOG_TAG,e.toString())
        }).add(compositeDisposable)
    }

    private fun bindTheText(term: String) {
        binding?.textView?.text = term
    }

}

private fun Disposable.add(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}
