package com.example.flex.services

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.flex.MainActivity
import com.example.flex.R
import com.example.flex.fragments.FragmentType
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavTransactionOptions

class MainFragmentService(supportFragmentManager: FragmentManager, private val fragments: List<Fragment>) :
    FragmentService(supportFragmentManager, fragments) {

    override fun init(activity: Activity, savedInstanceState: Bundle?) {
        fragNavController.apply {
            transactionListener = activity as MainActivity
            createEager = true
            rootFragments = fragments
            defaultTransactionOptions = FragNavTransactionOptions.newBuilder()
                .customAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .build()
            fragmentHideStrategy = FragNavController.DETACH_ON_NAVIGATE_HIDE_ON_SWITCH
        }

        fragNavController.initialize(FragmentType.LIBRARY.value, savedInstanceState)
    }
}