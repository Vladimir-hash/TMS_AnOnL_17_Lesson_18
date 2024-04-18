package com.example.lesson18

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserActivity : AppCompatActivity() {

    private lateinit var login: TextView
    private lateinit var password: TextView
    private lateinit var color: TextView
    private lateinit var optionOne: TextView
    private lateinit var optionTwo: TextView
    private lateinit var optionThree: TextView

    companion object {
        private const val USER_OBJECT = "user_object"
        private const val RED = "Red"
        private const val YELLOW = "Yellow"
        private const val WHITE = "White"

        fun launchIntent(context: Context, userParam: User): Intent {
            val intent = Intent(context, UserActivity::class.java)
            intent.putExtra(USER_OBJECT, userParam)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val userParams = parceIntent()

        initViews()
        changeParamViews(userParams)
        changeOptionsViews(userParams)
    }

    private fun parceIntent(): User? {
        val userParams = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(USER_OBJECT, User::class.java)
        } else {
            intent.getParcelableExtra(USER_OBJECT)
        }
        return userParams
    }

    private fun changeParamViews(userParams: User?) {
        login.text = userParams?.login
        password.text = userParams?.password
        color.text = userParams?.color
        changeColorBackground(color.text.toString())
    }

    private fun changeOptionsViews(userParams: User?) {

        if (userParams?.options?.get(0)?.isBlank() == false) {
            optionOne.visibility = View.VISIBLE
            optionOne.text = userParams.options[0]
        }

        if (userParams?.options?.get(1)?.isBlank() == false) {
            optionTwo.visibility = View.VISIBLE
            optionTwo.text = userParams.options[1]
        }

        if (userParams?.options?.get(2)?.isBlank() == false) {
            optionThree.visibility = View.VISIBLE
            optionThree.text = userParams.options[2]
        }
    }

    private fun initViews() {
        login = findViewById(R.id.current_login)
        password = findViewById(R.id.current_password)
        color = findViewById(R.id.notification)
        optionOne = findViewById(R.id.notification)
        optionTwo = findViewById(R.id.cookies)
    }

    private fun changeColorBackground(colorText: String) {
        when (colorText) {
            RED -> color.setBackgroundResource(R.drawable.white)
            YELLOW -> color.setBackgroundResource(R.drawable.white)
            WHITE -> {
                color.setBackgroundResource(R.drawable.white)
                color.setTextColor(resources.getColor(R.color.background))
            }
        }
    }
}