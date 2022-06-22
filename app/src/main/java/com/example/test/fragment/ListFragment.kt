package com.example.test.fragment

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.service.APIInterface
import android.widget.LinearLayout
import com.example.test.model.DataInfluencer
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.example.test.R
import com.example.test.service.APIClient
import com.example.test.utils.ProgressDialogUtil
import android.app.ProgressDialog
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONException
import android.widget.Toast
import com.example.test.adapter.InfluencerAdapter
import com.example.test.adapter.InfluencerAdapter.MyClickListener
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.test.DetailActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import java.util.ArrayList

class ListFragment : Fragment() {
    private var pendingrecycler: RecyclerView? = null
    private var llmV: LinearLayoutManager? = null
    private var apiInterface: APIInterface? = null
    private var linearIsEmpty: LinearLayout? = null
    private val pendingList: MutableList<DataInfluencer> = ArrayList()
    private val recyclerViewState: Parcelable? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_list, container, false)
        pendingrecycler = v.findViewById(R.id.listrecycler)
        linearIsEmpty = v.findViewById<View>(R.id.linearIsEmpty) as LinearLayout
        apiInterface = APIClient.getClient(context).create(APIInterface::class.java)
        iniPendingList()


//        pendingrecycler.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//            @Override
//            public void onScrollChanged() {
//                View view = (View) pendingrecycler.getChildAt(pendingrecycler.getChildCount() - 1);
//                if (view != null) {
//                    if (!pendingrecycler.canScrollVertically(1)) {
//                        if (isDone) {
//                            getPendingList();
//                        }
//                    }
//                    recyclerViewState = pendingrecycler.getLayoutManager().onSaveInstanceState();
//                }
//            }
//        });
        return v
    }

    private fun iniPendingList() {
        pendingrecycler!!.setHasFixedSize(true)
        llmV = LinearLayoutManager(activity)
        llmV!!.orientation = LinearLayoutManager.VERTICAL
        pendingrecycler!!.layoutManager = llmV
        getPendingList()
    }

    private val PAGE_SIZE = 10
    private var totalCount = 0
    private val isLastPage = false
    private val isLoading = false
    private val isBackendProcess = false
    var start = 0
    private var isDone = false
    private fun getPendingList() {
        try {
            apiInterface = APIClient.getClient(activity).create(APIInterface::class.java)
            val progressDialogUtil =
                ProgressDialogUtil(activity, ProgressDialog.STYLE_SPINNER, false)
            progressDialogUtil.show()
            val callCustomer = apiInterface?.influencerIgList()
            if (callCustomer != null) {
                callCustomer.enqueue(object : Callback<ResponseBody?> {
                    override fun onResponse(
                        call: Call<ResponseBody?>,
                        response: Response<ResponseBody?>
                    ) {
                        if (response.body() != null) {
                            totalCount = 200
                            pendingList.clear()
                            val jsonarray: JSONArray? = null
                            try {
                                val tmpList: MutableList<DataInfluencer> = ArrayList()
                                val array = JSONArray(response.body()!!.string())
                                for (i in 0 until array.length()) {
                                    val listData = DataInfluencer()
                                    val jsonobject = array.getJSONObject(i)
                                    listData.id = jsonobject.getInt("id")
                                    listData.name = jsonobject.getString("name")
                                    listData.username = jsonobject.getString("username")
                                    listData.engagement_rate = jsonobject.getInt("engagement_rate")
                                    listData.followers = jsonobject.getInt("followers")
                                    listData.avg_likes = jsonobject.getInt("avg_likes")
                                    listData.contactperson = jsonobject.getString("contactperson")
                                    tmpList.add(listData)
                                }
                                pendingList.addAll(tmpList)
                                setAdapter()
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                            if (pendingList.size > 0) {
                                linearIsEmpty!!.visibility = View.GONE
                            } else {
                                linearIsEmpty!!.visibility = View.VISIBLE
                            }
                            isDone = true
                        } else {
                            try {
                                val j = JSONObject(response.errorBody()!!.string())
                                Toast.makeText(context, j.getString("message"), Toast.LENGTH_LONG)
                                    .show()
                                linearIsEmpty!!.visibility = View.VISIBLE
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
        /* try {
            isLoading = false;

            apiInterface = APIClient.getClient(getActivity()).create(APIInterface.class);
            final ProgressDialogUtil progressDialogUtil = new ProgressDialogUtil(getActivity(), ProgressDialog.STYLE_SPINNER, false);
            progressDialogUtil.show();

            Call<String> callPendingList = apiInterface.influencerList();
            callPendingList.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    progressDialogUtil.dismiss();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.e("Retrofit Get", t.toString());
                    progressDialogUtil.dismiss();
                    linearIsEmpty.setVisibility(View.VISIBLE);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            linearIsEmpty.setVisibility(View.VISIBLE);
        }*/
    }

    fun setAdapter() {
        try {
            if (adapter != null) {
                adapter!!.notifyDataSetChanged()
            }
            adapter = InfluencerAdapter(pendingList, requireContext())
            pendingrecycler!!.adapter = adapter
            (adapter as InfluencerAdapter?)?.setOnItemClickListener(object : MyClickListener {
                override fun onItemClick(position: Int, v: View?) {
                    try {
                        if (v!!.id == R.id.btnViewDetail) {
                            val i = Intent(activity, DetailActivity::class.java)
                            i.putExtra("username", pendingList[position].username)
                            Log.d("username ", pendingList[position].username.toString())
                            startActivity(i)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
            pendingrecycler!!.layoutManager!!.onRestoreInstanceState(recyclerViewState)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private var adapter: RecyclerView.Adapter<*>? = null
    }
}