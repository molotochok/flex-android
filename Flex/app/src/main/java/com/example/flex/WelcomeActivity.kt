package com.example.flex

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.flex.fragments.WelcomeFragment1
import com.example.flex.services.WelcomeFragmentService
import com.ncapdevi.fragnav.FragNavController

class WelcomeActivity : AppCompatActivity(), WelcomeFragment1.OnFragmentInteractionListener, FragNavController.TransactionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTabTransaction(fragment: Fragment?, index: Int) {
        if(supportActionBar != null)
            fragmentService.setDisplayHomeAsUpEnabled(supportActionBar)
    }

    override fun onFragmentTransaction(fragment: Fragment?, transactionType: FragNavController.TransactionType) {
        if(supportActionBar != null)
            fragmentService.setDisplayHomeAsUpEnabled(supportActionBar)
    }

    private val fragments : List<Fragment> = listOf(
        WelcomeFragment1()
    )

    private val fragmentService = WelcomeFragmentService(supportFragmentManager, fragments)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        fragmentService.init(this, savedInstanceState)
    }
}
