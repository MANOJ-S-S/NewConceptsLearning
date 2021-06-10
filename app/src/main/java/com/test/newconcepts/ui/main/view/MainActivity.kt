package com.test.newconcepts.ui.main.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.newconcepts.R
import com.test.newconcepts.data.model.Questions
import com.test.newconcepts.data.repository.MainRepository
import com.test.newconcepts.ui.main.adapter.MyAdapter
import com.test.newconcepts.ui.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewmodel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)
        setupUI()



        /*This method observes the Questions alone and cannot be altered*

        like ,  viewmodel.getQuestions.postValue() will not be applicable because the returned value in LiveData and not MutableLiveData
         */
        viewmodel.getQuestions(context = this@MainActivity).observe(this,
                Observer {
                    it?.let { questions -> showAllQuestions(questions) }
                }
        )

        viewmodel.errorResponse.observe(this,
            Observer {
                it?.let { errorMsg -> showErrorMessage(errorMsg) }
            }
        )


    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

    }

    private fun setupUI() {

            recycle.layoutManager = LinearLayoutManager(this)
            adapter =
                MyAdapter(
                    arrayListOf()
                ,  this@MainActivity)
        recycle.addItemDecoration(
                DividerItemDecoration(
                    recycle.context,
                    (recycle.layoutManager as LinearLayoutManager).orientation
                )
            )
        recycle.adapter = adapter

    }

    private fun showAllQuestions(questions: Questions) {

        adapter.addData(questions.items)
        adapter.notifyDataSetChanged()

    }


    private fun showErrorMessage(error: String) {

        Toast.makeText(baseContext,error,Toast.LENGTH_SHORT).show()

    }

}