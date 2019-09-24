package com.example.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions) { question: Question -> questionClicked(question) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {

        rvQuestions.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuestions.adapter = questionAdapter
        createItemTouchHelper().attachToRecyclerView(rvQuestions)

        rvQuestions.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))

        //Populate the list with the questions
        for (i in Question.QUESTIONS.indices) {
            questions.add(Question(Question.QUESTIONS[i], Question.CORRECT_ANSWERS[i]))
        }
        questionAdapter.notifyDataSetChanged()
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        var callback = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            //Ability to move things up and down, disabled
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) : Boolean {
                return false
            }
            // When swiping left, check if answer true is correct
            // Otherwise check if answer false is correct (swipe right)
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (direction == ItemTouchHelper.LEFT) {
                    checkAnswer(position, true)
                }
                else {
                    checkAnswer(position, false)
                }
                questionAdapter.notifyDataSetChanged()
            }


        }
        return ItemTouchHelper(callback)

    }
    // Display correct answer when the question is clicked
    private fun questionClicked(question: Question) {
        Snackbar.make(constraint, getString(R.string.answer) + " ${question.corrAns}", Snackbar.LENGTH_SHORT).show()
    }

    // Gets the position of the question as a parameter, checks the corresponding
    // correct answer and assigns it to variable corrAnswer
    // Print the message depending whether the answer was correct or not
    // Also prints the correct answer
    private fun checkAnswer(qID: Int, answer: Boolean){
        val corrAnswer = Question.CORRECT_ANSWERS[qID]
        if (corrAnswer == answer) {
            Snackbar.make(constraint, getString(R.string.correct) + " $corrAnswer", Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(constraint, getString(R.string.incorrect) + " $corrAnswer", Snackbar.LENGTH_SHORT).show()
        }
    }
}
