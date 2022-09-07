package com.example.easyread

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.slider.Slider


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editBtn = findViewById<ImageButton>(R.id.ibEdit)
        val clearBtn = findViewById<ImageButton>(R.id.ibClear)
        val sbTextSize = findViewById<Slider>(R.id.sbTextSize)
        val tvText = findViewById<TextView>(R.id.tvText)

        sbTextSize.addOnChangeListener { _, value, _ ->
            tvText.textSize = value
        }

        editBtn.setOnClickListener {
            val input = EditText(this)
            input.hint = "Type Something..."
            input.inputType = InputType.TYPE_CLASS_TEXT
            input.isSingleLine = false
            input.setText(tvText.text)
            input.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    tvText.text = input.text
                }

                override fun afterTextChanged(s: Editable?) {}
            })
            val container = FrameLayout(this)
            val params = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.leftMargin = resources.getDimensionPixelSize(R.dimen.dialog_margin)
            params.rightMargin = resources.getDimensionPixelSize(R.dimen.dialog_margin)
            input.layoutParams = params
            container.addView(input)

            val dialog = MaterialAlertDialogBuilder(this)
                .setTitle("Edit Text")
                .setView(container)
                .setNegativeButton("Clear",null)
                .setPositiveButton("Done") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener {
                tvText.text = ""
                input.setText("")
            }

        }

        clearBtn.setOnClickListener {
         tvText.text = ""
        }
    }
}