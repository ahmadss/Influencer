package com.example.test.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.test.R
import android.widget.TextView
import com.example.test.utils.ProfileUtil
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.example.test.LoginActivity

class ProfilFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_profil, container, false)
        val username = v.findViewById<TextView>(R.id.txtUsername)
        val email = v.findViewById<TextView>(R.id.input_email)
        val category = v.findViewById<TextView>(R.id.input_category)
        val usertxt = ProfileUtil().getUser(activity)
        if (usertxt != null) {
            username.text = usertxt
        }
        val emailtxt = ProfileUtil().getEmail(activity)
        if (emailtxt != null) {
            email.text = emailtxt
        }
        val cattxt = ProfileUtil().getcategory(activity)
        if (cattxt != null) {
            category.text = cattxt
        }
        v.findViewById<View>(R.id.btnLogout).setOnClickListener {
            ProfileUtil().resetProfile(activity)
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        return v
    }
}