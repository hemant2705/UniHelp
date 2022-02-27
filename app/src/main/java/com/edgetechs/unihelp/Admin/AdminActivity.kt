package com.edgetechs.unihelp.Admin

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
import com.edgetechs.unihelp.Admin.frags.AllSchemeFragment
import com.edgetechs.unihelp.Admin.frags.NewSchemeFragment
import com.edgetechs.unihelp.R
import com.edgetechs.unihelp.Student.frags.studentProfile
import com.edgetechs.unihelp.Student.frags.studenthome
import com.edgetechs.unihelp.Student.frags.studentsMyApplications
import com.edgetechs.unihelp.common.Asking
import com.google.android.material.navigation.NavigationView


class AdminActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView
    var previousMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
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
                R.id.allScheme -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame,
                            AllSchemeFragment()
                        )
                        .addToBackStack("Profile")
                        .commit()
                    supportActionBar?.title = "All Schemes"
                    drawerLayout.closeDrawers()
                }
                R.id.newScheme-> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame,
                            NewSchemeFragment()
                        )
                        .addToBackStack("Release New Scheme")
                        .commit()
                    supportActionBar?.title = "Release New Scheme"
                    drawerLayout.closeDrawers()
                }
                R.id.logout -> {
                    val intent = Intent(this, Asking::class.java)
                    startActivity(intent)
                }
            }
            return@setNavigationItemSelectedListener true
        }

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

