package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.example.test.service.APIInterface
import android.widget.EditText
import android.os.Bundle
import com.example.test.R
import com.example.test.service.APIClient
import com.example.test.utils.ProgressDialogUtil
import android.app.ProgressDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import com.example.test.model.DataInfluencer
import org.json.JSONException
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

class DetailActivity : AppCompatActivity() {
    private var tvTitle: TextView? = null
    private var apiInterface: APIInterface? = null
    private var input_name: EditText? = null
    private var input_contact: EditText? = null
    private var input_followers: EditText? = null
    private var input_engagement_rate: EditText? = null
    private var input_avg_likes: EditText? = null
    private var input_avg_comments: EditText? = null
    private var username: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        input_name = findViewById(R.id.input_name)
        input_contact = findViewById(R.id.input_contact)
        input_followers = findViewById(R.id.input_followers)
        input_engagement_rate = findViewById(R.id.input_engagement_rate)
        input_avg_likes = findViewById(R.id.input_avg_likes)
        input_avg_comments = findViewById(R.id.input_avg_comments)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        tvTitle = toolbar.findViewById<View>(R.id.toolbar_title) as TextView
        tvTitle!!.text = "Detail Influencer"
        setSupportActionBar(toolbar)
        username = intent.getStringExtra("username")
        apiInterface = APIClient.getClient(this@DetailActivity).create(APIInterface::class.java)
        data
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        setOptionMenu(id)
        return super.onOptionsItemSelected(item)
    }

    private fun setOptionMenu(id: Int) {
        if (id == R.id.action_setting) {
//            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
//            startActivity(intent);
        }
    }


    private val data: Unit
        private get() {
            try {
                apiInterface = APIClient.getClient(this@DetailActivity).create(
                    APIInterface::class.java
                )
                val progressDialogUtil =
                    ProgressDialogUtil(this@DetailActivity, ProgressDialog.STYLE_SPINNER, false)
                progressDialogUtil.show()
                val url = "/api/influencerIg/$username"
                val callCustomer = apiInterface?.influencerIgData(url)
                if (callCustomer != null) {
                    callCustomer.enqueue(object : Callback<ResponseBody?> {
                        override fun onResponse(
                            call: Call<ResponseBody?>,
                            response: Response<ResponseBody?>
                        ) {
                            if (response.body() != null) {
                                try {
                                    val jsonobject = JSONObject(response.body()!!.string())
                                    val listData = DataInfluencer()
                                    listData.id = jsonobject.getInt("id")
                                    listData.name = jsonobject.getString("name")
                                    listData.followers = jsonobject.getInt("followers")
                                    listData.avg_comments = jsonobject.getInt("avg_comments")
                                    listData.engagement_rate = jsonobject.getInt("engagement_rate")
                                    listData.avg_likes = jsonobject.getInt("avg_likes")
                                    listData.contactperson = jsonobject.getString("contactperson")
                                    input_name!!.setText(listData.name)
                                    input_contact!!.setText(listData.contactperson)
                                    input_followers!!.setText(listData.followers.toString())
                                    input_engagement_rate!!.setText(listData.engagement_rate.toString())
                                    input_avg_likes!!.setText(listData.avg_likes.toString())
                                    input_avg_comments!!.setText(listData.avg_comments.toString())
                                } catch (e: JSONException) {
                                    e.printStackTrace()
                                } catch (e: IOException) {
                                    e.printStackTrace()
                                }
                            } else {
                                try {
                                    val j = JSONObject(response.errorBody()!!.string())
                                    Toast.makeText(
                                        applicationContext,
                                        j.getString("message"),
                                        Toast.LENGTH_LONG
                                    ).show()
                                } catch (e: Exception) {
                                    e.message
                                }
                            }
                            progressDialogUtil.dismiss()
                        }

                        override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                            Log.e("Retrofit Get", t.toString())
                            progressDialogUtil.dismiss()
                        }
                    })
                }
            } catch (e: Exception) {
                Log.d("error exception ", e.toString())
                e.printStackTrace()
            }
        }
}