package com.example.bharatquiz

object Constants {

    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTION: String = "total_question"
    const val CORRECT_ANSWER: String = "correct_answer"




    fun getQuestions(): ArrayList<Question>{
        val questionList = ArrayList<Question>()

        val que1 = Question(
                1,"What is the capital of India?",
                "Delhi", "New Delhi", "Kolkata","Lucknow",
                2
        )
        questionList.add(que1)

        val que2 = Question(
            2,"Who is the prime minister of India?",
            "Rahul gandhi", "Yogi Adityanath", "Narendra Modi","Yash Aggrawal",
            3
        )
        questionList.add(que2)

        val que3 = Question(
            3,"Which god has chakra as his/her Weapon?",
            "Shiva", "Bhrama", "Vishnu","Durga",
            3
        )
        questionList.add(que3)

        val que4 = Question(
            4,"Where is the Prem mandir is located?",
            "Delhi", "Vrindavan", "Mathura","Lucknow",
            2
        )
        questionList.add(que4)

        val que5 = Question(
            5,"What is the capital of Uttar pradesh?",
            "Delhi", "New Delhi", "Kolkata","Lucknow",
            4
        )
        questionList.add(que5)



        return questionList
    }


}