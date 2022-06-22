package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import androidx.appcompat.widget.AppCompatSpinner
import android.widget.FrameLayout
import android.widget.ImageButton
import com.example.test.service.APIInterface
import android.os.Bundle
import com.example.test.R
import android.content.Intent
import com.example.test.LoginActivity
import com.example.test.service.APIClient
import com.google.android.material.snackbar.Snackbar
import com.example.test.utils.ProgressDialogUtil
import android.app.ProgressDialog
import android.os.Handler
import com.example.test.request.RequestRegister
import okhttp3.ResponseBody
import com.example.test.utils.ProfileUtil
import com.example.test.MainActivity
import android.widget.Toast
import android.text.TextWatcher
import android.text.Editable
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AdapterView
import android.widget.TextView
import com.example.test.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

class RegisterBrandActivity : AppCompatActivity() {
    private var input_email: EditText? = null
    private var input_username: EditText? = null
    private var input_password: EditText? = null
    private var input_category: AppCompatSpinner? = null
    private var clearEmail: FrameLayout? = null
    private var clearUsername: FrameLayout? = null
    private var show: ImageButton? = null
    private var hide: ImageButton? = null
    private var apiInterface: APIInterface? = null
    private var parent_view: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_brand)
        parent_view = findViewById(android.R.id.content)
        input_email = findViewById(R.id.input_email)
        input_username = findViewById(R.id.input_username)
        input_password = findViewById(R.id.input_password)
        input_category = findViewById(R.id.input_category)
        clearEmail = findViewById(R.id.clearEmail)
        clearUsername = findViewById(R.id.clearUsername)
        show = findViewById(R.id.show)
        hide = findViewById(R.id.hide)
        input_email?.setFocusable(true)
        findViewById<View>(R.id.btnDaftar).setOnClickListener { submitRegister() }
        findViewById<View>(R.id.sign_in).setOnClickListener {
            val intent = Intent(this@RegisterBrandActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        apiInterface = APIClient.getClient(applicationContext).create(
            APIInterface::class.java
        )
        initProperty()
    }

    private fun submitRegister() {
        val email = input_email!!.text.toString().trim { it <= ' ' }
        val username = input_username!!.text.toString().trim { it <= ' ' }
        val category = input_category!!.selectedItem.toString().trim { it <= ' ' }
        val category_id = Utils.getIdCategory(category)
        val password = input_password!!.text.toString().trim { it <= ' ' }
        if (email == "") {
            input_email!!.error = "please input email"
            return
        }
        if (category_id == 0) {
            Snackbar.make(parent_view!!, "please selected category", Snackbar.LENGTH_SHORT).show()
            return
        }
        if (username == "") {
            input_username!!.error = "please input username"
            return
        }
        if (password == "") {
            input_password!!.error = "please input password"
            return
        }
        try {
            val progressDialogUtil =
                ProgressDialogUtil(this@RegisterBrandActivity, ProgressDialog.STYLE_SPINNER, false)
            progressDialogUtil.show()
            val data = RequestRegister(username, email, password, 1, category_id)
            val callLogin = apiInterface!!.doSignUp(data)
            callLogin.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        try {
                            Log.d("tag data ", "data " + response.body()!!.string())
                        } catch (e: IOException) {
                            e.printStackTrace()
                            Log.d("tag data ", "data $e")
                        }
                        val user = response.body().toString()
                        ProfileUtil().save(
                            applicationContext,
                            input_username!!.text.toString(),
                            input_category!!.selectedItem.toString(),
                            input_email!!.text.toString()
                        )
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        try {
                            Toast.makeText(
                                this@RegisterBrandActivity,
                                response.body().toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        } catch (e: Exception) {
                            e.message
                        }
                    }
                    progressDialogUtil.dismiss()
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.d("tag error ", t.message.toString())
                    Toast.makeText(
                        this@RegisterBrandActivity,
                        t.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    progressDialogUtil.dismiss()
                }
            })
        } catch (ex: Exception) {
            Log.d("tag error 1", ex.message.toString())
            ex.printStackTrace()
            Snackbar.make(parent_view!!, ex.toString(), Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun initProperty() {
        input_email!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (input_email!!.text.toString().length == 0) {
                    input_email!!.visibility = View.VISIBLE
                    clearEmail!!.visibility = View.INVISIBLE
                } else {
                    input_email!!.error = null
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                clearEmail!!.visibility = View.VISIBLE
            }
        })
        input_username!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (input_username!!.text.toString().length == 0) {
                    input_username!!.visibility = View.VISIBLE
                    clearUsername!!.visibility = View.INVISIBLE
                } else {
                    input_username!!.error = null
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                clearUsername!!.visibility = View.VISIBLE
            }
        })
        input_password!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (input_password!!.text.toString().length == 0) {
                    show!!.visibility = View.INVISIBLE
                    hide!!.visibility = View.INVISIBLE
                } else if (hide!!.visibility == View.VISIBLE) {
                    show!!.visibility = View.INVISIBLE
                } else {
                    show!!.visibility = View.VISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
        show!!.setOnClickListener {
            input_password!!.transformationMethod = null
            show!!.visibility = View.INVISIBLE
            hide!!.visibility = View.VISIBLE
        }
        hide!!.setOnClickListener {
            input_password!!.transformationMethod = PasswordTransformationMethod()
            show!!.visibility = View.VISIBLE
            hide!!.visibility = View.INVISIBLE
        }
        clearEmail!!.setOnClickListener {
            input_email!!.setText("")
            input_email!!.visibility = View.VISIBLE
        }
        clearUsername!!.setOnClickListener {
            input_username!!.setText("")
            input_username!!.visibility = View.VISIBLE
        }
        val categorys = resources.getStringArray(R.array.category)
        val array = ArrayAdapter(this, R.layout.simple_spinner_item, categorys)
        array.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        input_category!!.adapter = array
        input_category!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                isEnabled(0)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
            fun isEnabled(position: Int): Boolean {
                return if (position == 0) false else true
            }
        }
        input_category!!.setSelection(0, false)
        val v = input_category!!.selectedView
        if (input_category!!.selectedItemPosition == 0) {
            (v as TextView).setTextColor(resources.getColor(R.color.grey_40))
        } else {
            (v as TextView).setTextColor(resources.getColor(R.color.overlay_dark_90))
        }
    }

    var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        //Checking for fragment count on backstack
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else if (!doubleBackToExitPressedOnce) {
            doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Press BACK again to exit.", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        } else {
            super.onBackPressed()
            return
        }
    }
}