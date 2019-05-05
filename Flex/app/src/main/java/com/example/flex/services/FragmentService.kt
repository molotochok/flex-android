package com.example.flex.services

import android.app.Activity
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentManager
import com.example.flex.R
import com.example.flex.fragments.FragmentType
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ncapdevi.fragnav.FragNavController

// Service which helps managing fragments
abstract class FragmentService(supportFragmentManager: FragmentManager, private val fragments : List<Fragment>) {

    protected var fragNavController: FragNavController = FragNavController(supportFragmentManager, R.id.container)

    //region Public Methods
    abstract fun init(activity : Activity, savedInstanceState : Bundle?)

    fun setDisplayHomeAsUpEnabled(supportActionBar: ActionBar?){
        supportActionBar!!.setDisplayHomeAsUpEnabled(fragNavController.isRootFragment.not())
    }
    fun onBackPressed() : Boolean{
        return when(fragNavController.isRootFragment.not()){
            true -> {
                fragNavController.popFragment()
                true
            }
            else -> false
        }
    }
    fun onSaveInstanceState(outState: Bundle?){
        fragNavController.onSaveInstanceState(outState)
    }
    fun onOptionsItemSelected(item: MenuItem){
        when (item.itemId) {
            android.R.id.home -> fragNavController.popFragment()
        }
    }
    fun pushFragment(fragment : Fragment){
        fragNavController.pushFragment(fragment)
    }

    fun setOnNavigationItemSelectedListener(navigation : BottomNavigationView){
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    //endregion

    //region Private Methods
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_library -> {
                changeFragment(FragmentType.LIBRARY)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_downloads -> {
                changeFragment(FragmentType.DOWNLOADS)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                changeFragment(FragmentType.SETTINGS)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun changeFragment(fragmentType : FragmentType){
        fragNavController.switchTab(fragmentType.value)
    }
    //endregion
}