package com.example.lesson18

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    companion object{
        const val IS_EMPTY =""
    }
    private lateinit var login: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var textInputLayoutOne: TextInputLayout
    private lateinit var textInputLayoutTwo: TextInputLayout
    private lateinit var optionOne: CheckBox
    private lateinit var optionTwo: CheckBox
    private lateinit var radioOne: RadioButton
    private lateinit var radioTwo: RadioButton
    private lateinit var radioThree: RadioButton
    private lateinit var logInButton: Button
    private lateinit var textWatcher: TextWatcher

    override fun onCreate(savedInstanceState: Bundle?,) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        login.addTextChangedListener(textWatcher)
        password.addTextChangedListener(textWatcher)

        logInButton.setOnClickListener {
            val useParam = initUserParam()
            startActivity(UserActivity.launchIntent(this, useParam))
        }
    }


    private fun initViews() {
        logInButton = findViewById(R.id.button_reg)

        textInputLayoutOne = findViewById(R.id.email_container)
        textInputLayoutTwo = findViewById(R.id.password_container)

        login = findViewById(R.id.email_edit_text)
        password = findViewById(R.id.password_edit_text)

        radioOne = findViewById(R.id.radioButton1)
        radioTwo = findViewById(R.id.radioButton2)
        radioThree = findViewById(R.id.radioButton3)

        optionOne = findViewById(R.id.checkBox1)
        optionTwo = findViewById(R.id.checkBox2)

        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int ) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                valudationEnable()
                valudationError()
            }
            override fun afterTextChanged(s: Editable?) {}
        }
    }
    private fun initUserParam(): User {
        return User(
            login = login.text.toString().trim(),
            password = password.text.toString().trim(),
            color = checkColor(),
            options = checkOptions()
        )
    }
    private fun checkColor(): String {
        val result = when {
            radioOne.isChecked -> radioOne.text
            radioTwo.isChecked -> radioTwo.text
            radioThree.isChecked -> radioThree.text
            else -> IS_EMPTY
        }
        return result.toString()
    }
    private fun checkOptions(): List<String> {
        val result = mutableListOf<String>()

        if (optionOne.isChecked) {
            result.add(optionOne.text.toString())
        } else {
            result.add(IS_EMPTY)
        }
        if (optionTwo.isChecked) {
            result.add(optionTwo.text.toString())
        } else {
            result.add(IS_EMPTY)
        }
        return  result
    }

    private fun valudationEnable() {
        val logText = login.text?.toString()?.trim()
        val passText = login.text?.toString()?.trim()

        logInButton.isEnabled = logText!!.length > 5 && passText!!.length > 5

        if (logText.length > 5 && passText!!.length > 5) {
            logInButton.isEnabled = true
        }
    }
    private fun valudationError() {
        val logText = login.text?.toString()?.trim()
        val passText = login.text?.toString()?.trim()

        if(logText != null) {
            if(logText.length < 5 && !login.isFocused) {
                textInputLayoutOne.isErrorEnabled = true
                textInputLayoutOne.error = resources.getString(R.string.error_login)
            } else {
                textInputLayoutOne.isErrorEnabled = false
            }
        }
        if(passText != null) {
            if(passText.length < 5 && !password.isFocused) {
                textInputLayoutTwo.isErrorEnabled = true
                textInputLayoutTwo.error = resources.getString(R.string.incorrect_password)
            } else {
                textInputLayoutTwo.isErrorEnabled = false
            }
        }
    }
}