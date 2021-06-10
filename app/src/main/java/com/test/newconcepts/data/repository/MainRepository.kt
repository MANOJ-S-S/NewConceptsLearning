package com.test.newconcepts.data.repository

import android.content.Context
import com.test.newconcepts.data.api.RetrofitBuilder
import com.test.newconcepts.ui.main.viewmodel.MainViewModel
import com.test.newconcepts.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainRepository {




    companion object {

        private var mainRepository: MainRepository? = null

        fun instance(): MainRepository {
            if (mainRepository == null) synchronized(MainRepository) {
                mainRepository = MainRepository()
            }
            return mainRepository!!
        }

    }





    /* Returns the disposable from rx java */

   fun getAllQuestions(viewModel: MainViewModel, context: Context) : Disposable {

       return RetrofitBuilder.getInstance(context, Utils.BASE_URL)
           .getQuestions("desc", "activity", "stackoverflow")
           .observeOn(AndroidSchedulers.mainThread())
           .subscribeOn(Schedulers.io())
           .subscribe({
               viewModel.questionsList.value = it
           }, {
               viewModel.errorResponse.value = it.message
           })

   }


}