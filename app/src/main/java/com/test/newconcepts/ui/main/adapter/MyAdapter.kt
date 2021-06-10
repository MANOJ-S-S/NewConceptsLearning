package com.test.newconcepts.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.newconcepts.R
import com.test.newconcepts.data.model.Item
import com.test.newconcepts.data.model.Questions
import kotlinx.android.synthetic.main.recycle_layout.view.*

class MyAdapter(
    private val questions: ArrayList<Item>, private val context: Context
) : RecyclerView.Adapter<MyAdapter.QuestionViewHolder>() {

    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(question: Item, context: Context) {
            itemView.ques_title.text = question.title
            itemView.ques_link.text = question.link
            Glide.with(context)
                .load(question.owner.profile_image)
                .into(itemView.user_foto)
            itemView.user_name.text = question.owner.display_name
            itemView.user_stacklink.text = question.owner.link
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        QuestionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycle_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = questions.size

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) =
        holder.bind(questions[position],context)

    fun addData(list: List<Item>) {
        questions.addAll(list)
    }

}
