package com.example.sampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import com.example.sampleapp.databinding.ActivityMainBinding
import com.example.sampleapp.movielist.MovieListActivity


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.edEmailAddress?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding?.edEmailError?.visibility = View.INVISIBLE

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (TextUtils.isEmpty(p0)) {
                    binding?.edEmailError?.visibility = View.VISIBLE
                    binding?.edEmailAddress?.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(p0!!).matches()) {
                    binding?.edEmailError?.visibility = View.VISIBLE
                    binding?.edEmailAddress?.requestFocus();
                } else {
                    binding?.edEmailError?.visibility = View.INVISIBLE
                    validateFields()

                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if (TextUtils.isEmpty(p0)) {
                    binding?.edEmailError?.visibility = View.INVISIBLE
                }
            }

        })
        binding?.edpassword?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding?.edpasswordError?.visibility = View.INVISIBLE

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                when {
                    TextUtils.isEmpty(p0) -> {
                        binding?.edpasswordError?.visibility = View.VISIBLE
                        binding?.edpassword?.requestFocus();
                    }
                    p0?.length!! !in 15 downTo 8 -> {
                        binding?.edpasswordError?.visibility = View.VISIBLE
                        binding?.edpassword?.requestFocus();
                    }
                    else -> {
                        binding?.edpasswordError?.visibility = View.INVISIBLE
                        validateFields()
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if (TextUtils.isEmpty(p0)) {
                    binding?.edpasswordError?.visibility = View.INVISIBLE

                }
            }

        })
        binding?.btnSignUp?.setOnClickListener {
            val myIntent = Intent(this, MovieListActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }

    private fun validateFields() {
        if (Patterns.EMAIL_ADDRESS.matcher(binding?.edEmailAddress?.text.toString()).matches()
            && binding?.edpassword?.text.toString().length in 15 downTo 8
        ) {
            binding?.btnSignUp?.isEnabled = true
        }
    }

}