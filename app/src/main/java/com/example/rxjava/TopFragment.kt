package com.example.rxjava

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.example.rxjava.databinding.FragmentTopBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class TopFragment(private val messanger:Messenger): BaseFragment<FragmentTopBinding>()  {
    //region initialize variables
    override val LOG_TAG = "TOP_FRAGMENT"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTopBinding = FragmentTopBinding::inflate
    //endregion

    //region setup function
    override fun setup() {
        observeDataOnEditText()
    }
    //endregion

    //region Observable
    /**
     * this function will create hot observable and connect it and observe it on the ui
     * @param emitter of type ObservableEmitter<Type>
     * @return Unit
     * @author Wesam N. Shawqi
     */

    private fun observeDataOnEditText() {
        val observable = PublishSubject.create<String> { emitter ->
            emitData(emitter)
        }.debounce(Constants.ONE_AND_HALF_SECOND,TimeUnit.MILLISECONDS).publish()
        observable.connect()
        observable.observeOn(AndroidSchedulers.mainThread()).subscribe(::sendData,::sendError).add(compositeDisposable)
    }

    /**
     * this function will emit the text from the EditText to the observer, it should be called on create
     * @param emitter of type ObservableEmitter<Type>
     * @return Unit
     * @author Wesam N. Shawqi
     */
    private fun emitData(emitter:ObservableEmitter<String>) {
        binding?.editText?.doOnTextChanged { text, start, before, count ->
            emitter.onNext(text.toString())
        }
    }
    /**
     * this function will send the data to another fragment using messenger interface, it should be called on subscribe
     * @param textFromEditText
     * @return Unit
     * @author Wesam N. Shawqi
     */

    private fun sendData(textFromEditText: String?) {
        textFromEditText?.let {
            messanger.sendData(textFromEditText)
        }
    }
    /**
     * this function will show error on Logcat, it should be called on subscribe
     * @param throwable
     * @return Unit
     * @author Wesam N. Shawqi
     */

    private fun sendError(throwable: Throwable?) {
        log(throwable.toString())
    }

    //endregion
}




