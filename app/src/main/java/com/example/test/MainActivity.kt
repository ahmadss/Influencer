package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.test.R
import android.os.Bundle
import com.example.test.utils.BottomNavigationViewHelper
import android.app.ActivityManager
import android.os.Handler
import android.text.InputFilter
import android.text.InputFilter.AllCaps
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.test.fragment.ProfilFragment
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.test.fragment.ListFragment

class MainActivity : AppCompatActivity() {
    private var tvTitle: TextView? = null
    private var tag: String? = ""
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_list -> {
                    setFragment(R.id.navigation_list, "BERANDA")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profil -> {
                    setFragment(R.id.navigation_profil, "PROFIL")
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        tvTitle = toolbar.findViewById<View>(R.id.toolbar_title) as TextView
        tvTitle!!.text = "HOME"
        setSupportActionBar(toolbar)
        val navigation = findViewById<View>(R.id.navigation) as BottomNavigationView
        BottomNavigationViewHelper.disableShiftMode(navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        tag = intent.getStringExtra("tag")
        if (tag == null) {
            setFragment(R.id.navigation_list, "BERANDA")
        } else {
            if (tag == "tagBeranda") {
                setFragment(R.id.navigation_list, "BERANDA")
                navigation.menu.findItem(R.id.navigation_list).isChecked = true
            } else if (tag == "tagProfil") {
                setFragment(R.id.navigation_profil, "PROFIL")
                navigation.menu.findItem(R.id.navigation_profil).isChecked = true
            }
        }
    }

    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                Log.i("isMyServiceRunning?", true.toString() + "")
                return true
            }
        }
        Log.i("isMyServiceRunning?", false.toString() + "")
        return false
    }

    private fun setFragment(id: Int, title: String) {
        val fragment: Fragment
        val fragmentManager = supportFragmentManager
        tvTitle!!.text = title
        tvTitle!!.filters = arrayOf<InputFilter>(AllCaps())
        if (id == R.id.navigation_list) {
            fragment = ListFragment()
            fragmentManager.beginTransaction().replace(R.id.main_frame, fragment).commit()
        } else if (id == R.id.navigation_profil) {
            fragment = ProfilFragment()
            fragmentManager.beginTransaction().replace(R.id.main_frame, fragment).commit()
        }
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

    override fun onDestroy() {
//        unregisterReceiver(mReciver);
//        stopService(mServiceIntent);
        Log.i("MAINACT", "onDestroy!")
        super.onDestroy()
    }
}