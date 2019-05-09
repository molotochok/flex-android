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
import com.ncapdevi.fragnav.FragNavTransactionOptions

// Service which helps managing fragments
class FragmentService(supportFragmentManager: FragmentManager, private val fragments : List<Fragment>) {

    private var fragNavController: FragNavController = FragNavController(supportFragmentManager, R.id.container)

    //region Public Methods
    fun init(activity : FragNavController.TransactionListener, savedInstanceState : Bundle?) {
        fragNavController.apply {
            transactionListener = activity
            createEager = true
            rootFragments = fragments
            defaultTransactionOptions = FragNavTransactionOptions.newBuilder()
                .customAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_right
                )
                .build()
            fragmentHideStrategy = FragNavController.DETACH_ON_NAVIGATE_HIDE_ON_SWITCH
        }

        fragNavController.initialize(FragNavController.TAB3, savedInstanceState)
    }

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