package com.edgetechs.unihelp.Student

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.edgetechs.unihelp.R
import com.edgetechs.unihelp.Student.frags.studentProfile
import com.edgetechs.unihelp.Student.frags.studenthome
import com.edgetechs.unihelp.Student.frags.studentsMyApplications
import com.edgetechs.unihelp.common.Asking
import com.google.android.material.navigation.NavigationView


class Student_Activity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView
    var previousMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_)
        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinateLayout)
        frameLayout = findViewById(R.id.frame)
        toolbar = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.navigationView)
        setupToolbar()
        openHome()

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener {
            if (previousMenuItem != null) {
                previousMenuItem?.isChecked = false
            }
            it.isCheckable = true
            it.isChecked = true
            previousMenuItem = it

            when (it.itemId) {
                R.id.home-> {
                    openHome()
                    drawerLayout.closeDrawers()
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame,
                            studentProfile()
                        )
                        .addToBackStack("Profile")
                        .commit()
                    supportActionBar?.title = "Profile"
                    drawerLayout.closeDrawers()
                }
                R.id.application-> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame,
                            studentsMyApplications()
                        )
                        .addToBackStack("My Applications")
                        .commit()
                    supportActionBar?.title = "My Applications"
                    drawerLayout.closeDrawers()
                }
                R.id.logout -> {
                    val intent = Intent(this, Asking::class.java)
                    startActivity(intent)
                }
            }
            return@setNavigationItemSelectedListener true


        }

        val sharedPreferences= getSharedPreferences("theKey", Context.MODE_PRIVATE)
        val value1 = sharedPreferences.getString("fullname", "0").toString()
        val value2=sharedPreferences.getString("institutename","0").toString()
        val value3= sharedPreferences.getString("enrolment","0").toString()
        val value4= sharedPreferences.getString("mobile","0").toString()


        sharedPreferences.edit().putBoolean("isLogged",true).apply()


        val navigationView = findViewById(R.id.navigationView) as NavigationView
        val header = navigationView.getHeaderView(0)
       val username = header.findViewById<View>(R.id.txtUsername) as TextView
        val usermobile = header.findViewById<View>(R.id.txtMobile) as TextView
        username.setText(value1)
        usermobile.setText(value2)
    }



    fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Toolbar title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    fun openHome() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frame,
                studenthome()
            )
            .addToBackStack("Home")
            .commit()
        supportActionBar?.title = "UniHelp"
        navigationView.setCheckedItem(R.id.home)

    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frame)
        when (frag) {
            !is studenthome -> openHome()
            else -> finishAffinity()

        }
    }
}

