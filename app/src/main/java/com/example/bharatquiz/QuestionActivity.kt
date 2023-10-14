package com.example.bharatquiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuestionActivity : AppCompatActivity(), OnClickListener{

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOption: Int = 0
    private var mUserName : String? = null
    private var mCorrectAnswer: Int = 0

    private var progressBar: ProgressBar? = null
    private var tvProgressBar: TextView? = null
    private var tvQuestion: TextView? = null

    private var tvOne: TextView? = null
    private var tvTwo: TextView? = null
    private var tvThree: TextView? = null
    private var tvFour: TextView? = null
    private var btnSubmit : Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progressBar)
        tvProgressBar = findViewById(R.id.tvProgress)
        tvQuestion = findViewById(R.id.tvQuestion)
        tvOne = findViewById(R.id.tvOne)
        tvTwo = findViewById(R.id.tvTwo)
        tvThree = findViewById(R.id.tvThree)
        tvFour = findViewById(R.id.tvFour)
        btnSubmit = findViewById(R.id.btnSubmit)

        tvOne?.setOnClickListener(this)
        tvTwo?.setOnClickListener(this)
        tvThree?.setOnClickListener(this)
        tvFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        mQuestionList = Constants.getQuestions()


        setQuestion()
    }

    private fun setQuestion() {
        defaultOptionView()
        val question: Question = mQuestionList!![mCurrentPosition - 1]
        progressBar?.progress = mCurrentPosition
        tvProgressBar?.text = "$mCurrentPosition/${progressBar?.max}"
        tvQuestion?.text = question.question
        tvOne?.text = question.optionOne
        tvTwo?.text = question.optionTwo
        tvThree?.text = question.optionThree
        tvFour?.text = question.optionFour

        if (mCurrentPosition == mQuestionList!!.size){
            btnSubmit?.text = "FINISH"
        }else{
            btnSubmit?.text = "SUBMIT"
        }
    }

    private fun defaultOptionView(){
        val options = ArrayList<TextView>()
        tvOne?.let {
            options.add(0, it)
        }
        tvTwo?.let {
            options.add(1, it)
        }
        tvThree?.let {
            options.add(2, it)
        }
        tvFour?.let {
            options.add(3, it)
        }

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.option_border
            )
        }

    }

    private fun selectedOption(tv:TextView, selectedOptionNum: Int){
        defaultOptionView()

        mSelectedOption = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,
            R.drawable.option_border
        )
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tvOne -> {
                tvOne?.let {
                    selectedOption(it,1)
                }
            }

            R.id.tvTwo -> {
                tvTwo?.let {
                    selectedOption(it,2)
                }
            }

            R.id.tvThree -> {
                tvThree?.let {
                    selectedOption(it,3)
                }
            }

            R.id.tvFour -> {
                tvFour?.let {
                    selectedOption(it,4)
                }
            }

            R.id.btnSubmit -> {
               if (mSelectedOption == 0){
                   mCurrentPosition++

                   when{
                       mCurrentPosition <= mQuestionList!!.size ->{
                           setQuestion()
                       }
                       else -> {
                           val intent = Intent(this, ResultActivity::class.java)
                           intent.putExtra(Constants.USER_NAME, mUserName)
                           intent.putExtra(Constants.CORRECT_ANSWER, mCorrectAnswer)
                           intent.putExtra(Constants.TOTAL_QUESTION, mQuestionList?.size)
                           startActivity(intent)
                           finish()
                       }
                   }
               }else{
                   val question = mQuestionList?.get(mCurrentPosition -1)
                   if (question!!.correctAnswer != mSelectedOption){
                       answerView(mSelectedOption, R.drawable.wrong_option_border)
                   }else{
                       mCorrectAnswer++
                   }
                   answerView(question.correctAnswer, R.drawable.correct_option_border)

                   if (mCurrentPosition == mQuestionList!!.size){
                       btnSubmit?.text = "FINISH"
                   }else{
                       btnSubmit?.text = "Go To Next Question"
                   }

                   mSelectedOption = 0

               }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
           1 -> {
               tvOne?.background = ContextCompat.getDrawable(
                   this,
                   drawableView
               )
           }
            2 -> {
                tvTwo?.background = ContextCompat.getDrawable(
                    this@QuestionActivity,
                    drawableView
                )
            }
            3 -> {
                tvThree?.background = ContextCompat.getDrawable(
                    this@QuestionActivity,
                    drawableView
                )
            }
            4 -> {
                tvFour?.background = ContextCompat.getDrawable(
                    this@QuestionActivity,
                    drawableView
                )
            }

        }
    }
}