package com.example.jetpackdesign.util

import android.util.Patterns
import com.example.jetpackdesign.util.Util.Companion.isEmailValid
import javax.xml.validation.Validator

class Util {

    companion object {

        fun String.isEmailValid(): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(this).find()
        }

    }
}