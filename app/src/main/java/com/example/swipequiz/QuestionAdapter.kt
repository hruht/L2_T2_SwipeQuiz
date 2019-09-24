package com.example.swipequiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuestionAdapter(private val questions: List<Question>, val clickListener: (Question) -> Unit) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return questions.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions[position], clickListener)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvQuestion: TextView = itemView.findViewById(android.R.id.text1)

        fun bind (question: Question, clickListener: (Question) -> Unit) {
            tvQuestion.text = question.question
            itemView.setOnClickListener { clickListener(question)}
        }
    }

}