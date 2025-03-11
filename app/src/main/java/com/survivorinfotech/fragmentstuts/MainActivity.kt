package com.survivorinfotech.fragmentstuts

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    var cartval: Int = 7
    lateinit var bottomNav: BottomNavigationView
    lateinit var myToolbar: Toolbar

    lateinit var regDataSp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNav = findViewById(R.id.bottomNav)
        myToolbar = findViewById(R.id.myToolBar)
        setSupportActionBar(myToolbar)

        regDataSp = getSharedPreferences("regdatasp", Context.MODE_PRIVATE)


        supportActionBar?.title = regDataSp.getString("useremail","")
        supportActionBar?.subtitle = regDataSp.getString("userpass","")


        myToolbar.setNavigationIcon(R.drawable.icon_home)
        updateCardbadge()

        bottomNav.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.bnHome ->{


                    replaceFragment(HomeFragment())
                    supportActionBar?.title = "Home Fragment"
                    supportActionBar?.subtitle = "This is just Test"
                }

                R.id.bnProfile ->
                    replaceFragment(ProfileFragment())

                R.id.bnCart -> {
                    cartval = 0
                    updateCardbadge()
                    val i = Intent(this,RegistrationActivity::class.java)
                    startActivity(i)
                }
            }
            true
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.testContainer,HomeFragment()).commit()
        }
    }

    private fun updateCardbadge() {
        val badgeCart: BadgeDrawable = bottomNav.getOrCreateBadge(R.id.bnCart)
        if (cartval === 0) {
            badgeCart.isVisible = false
        } else {
            badgeCart.isVisible = true
            badgeCart.number = cartval
            badgeCart.backgroundColor = ContextCompat.getColor(this, R.color.light_green)
            badgeCart.badgeTextColor = ContextCompat.getColor(this, R.color.red)
            badgeCart.verticalOffset = -5
            badgeCart.horizontalOffset = 5

        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.testContainer, fragment)
//        Add the transaction that allow backpress / Backstack to Navigation
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}