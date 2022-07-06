package com.example.submission1intermediate

import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatEditText

class MyEditText:AppCompatEditText{

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(context: Context): super(context){
        init()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs){
        init()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr){
        init()

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        transformationMethod = PasswordTransformationMethod.getInstance()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun init(){
        inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        setHint(R.string.Password)
        setAutofillHints(AUTOFILL_HINT_PASSWORD)
        addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if (!p0.isNullOrEmpty() && p0.length < 6){
                    error = context.getString(R.string.error_password)
                } else if (p0.isNullOrEmpty()){
                    error = context.getString(R.string.error_blank)
                }


            }

            override fun afterTextChanged(p0: Editable?) {}

        })
    }


}