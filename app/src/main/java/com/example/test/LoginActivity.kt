package com.example.test

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import com.example.test.service.APIInterface
import android.os.Bundle
import com.example.test.R
import com.example.test.service.APIClient
import com.example.test.utils.ProfileUtil
import android.content.Intent
import com.example.test.MainActivity
import com.example.test.utils.ProgressDialogUtil
import android.app.ProgressDialog
import com.example.test.request.RequestLogin
import okhttp3.ResponseBody
import android.widget.Toast
import android.text.TextWatcher
import android.text.Editable
import android.text.method.PasswordTransformationMethod
import com.example.test.LoginActivity
import android.content.DialogInterface
import android.os.Handler
import android.view.View
import com.example.test.RegisterInfluencerActivity
import com.example.test.RegisterBrandActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    private var input_username: EditText? = null
    private var input_password: EditText? = null
    private var clearEmailLogin: FrameLayout? = null
    private var show: ImageButton? = null
    private var hide: ImageButton? = null
    private var apiInterface: APIInterface? = null
    private var parent_view: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        parent_view = findViewById(android.R.id.content)
        input_username = findViewById(R.id.input_username)
        input_password = findViewById(R.id.input_password)
        clearEmailLogin = findViewById(R.id.clearEmailLogin)
        show = findViewById(R.id.show)
        hide = findViewById(R.id.hide)
        input_username?.setFocusable(true)
        findViewById<View>(R.id.btnLogin).setOnClickListener { submitLogin() }
        findViewById<View>(R.id.sign_up).setOnClickListener { showSingleChoiceDialog() }
        apiInterface = APIClient.getClient(applicationContext).create(
            APIInterface::class.java
        )
        val userPicker = ProfileUtil().getUser(applicationContext)
        if (userPicker != null) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        initProperty()
    }

    private fun submitLogin() {
        val email = input_username!!.text.toString().trim { it <= ' ' }
        val password = input_password!!.text.toString().trim { it <= ' ' }
        if (email == "") {
            input_username!!.error = "please input username"
            return
        }
        if (password == "") {
            input_password!!.error = "please input password"
            return
        }
        try {
            val progressDialogUtil =
                ProgressDialogUtil(this@LoginActivity, ProgressDialog.STYLE_SPINNER, false)
            progressDialogUtil.show()
            val data = RequestLogin(email, password)
            val callLogin = apiInterface!!.doLogin(data)
            callLogin.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val user = response.body().toString()
                        ProfileUtil().save(
                            applicationContext,
                            input_username!!.text.toString(),
                            "Brand",
                            "test@gmail.com"
                        )
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                        //
                    } else {
                        try {
                            Toast.makeText(
                                this@LoginActivity,
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
                    Toast.makeText(this@LoginActivity, "Invalid connection", Toast.LENGTH_SHORT)
                        .show()
                    progressDialogUtil.dismiss()
                }
            })
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun initProperty() {
        input_username!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (input_username!!.text.toString().length == 0) {
                    input_username!!.visibility = View.VISIBLE
                    clearEmailLogin!!.visibility = View.INVISIBLE
                } else {
                    input_username!!.error = null
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                clearEmailLogin!!.visibility = View.VISIBLE
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
        clearEmailLogin!!.setOnClickListener {
            input_username!!.setText("")
            input_username!!.visibility = View.VISIBLE
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

    private var single_choice_selected: String? = null
    private fun showSingleChoiceDialog() {
        single_choice_selected = PILIHAN[0]
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Model User")
        builder.setSingleChoiceItems(PILIHAN, 0) { dialogInterface, i ->
            single_choice_selected = PILIHAN[i]
        }
        builder.setPositiveButton("OK") { dialogInterface, i ->
            if (single_choice_selected == "Influencer") {
                val intent = Intent(this@LoginActivity, RegisterInfluencerActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this@LoginActivity, RegisterBrandActivity::class.java)
                startActivity(intent)
            }
            finish()
            //                Snackbar.make(parent_view, "selected : " + single_choice_selected, Snackbar.LENGTH_SHORT).show();
        }
        builder.setNegativeButton("CANCEL", null)
        builder.show()
    }

    companion object {
        private val PILIHAN = arrayOf(
            "Influencer", "Brand"
        )
    }
}