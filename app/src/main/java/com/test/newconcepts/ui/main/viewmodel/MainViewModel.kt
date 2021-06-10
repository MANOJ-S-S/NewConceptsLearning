package com.test.newconcepts.ui.main.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.newconcepts.data.model.Questions
import com.test.newconcepts.data.repository.MainRepository
import io.reactivex.disposables.CompositeDisposable


class MainViewModel  : ViewModel() {


    var mainRepository = MainRepository.instance()
    var questionsList = MutableLiveData<Questions>()
    var errorResponse =   MutableLiveData<String>()
    private var compositeDisposable = CompositeDisposable()


    /*This method returns the data as LiveData because the Questions values should  be observed alone and  should not be edited
    *
    * eg        viewmodel.questionsList.postValue() should not be called from MainActivity.
    *
    * */

    fun getQuestions (context: Context) : LiveData<Questions> {
        compositeDisposable.add(mainRepository.getAllQuestions(this,context))
        return  questionsList
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}