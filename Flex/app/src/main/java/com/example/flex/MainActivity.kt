package com.example.flex

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import kotlinx.android.synthetic.main.adapter_movie_layout.view.*
import com.example.flex.fragments.DownloadsFragment
import com.example.flex.fragments.LibraryFragment
import com.example.flex.fragments.SettingsFragment
import com.ncapdevi.fragnav.FragNavController
import androidx.fragment.app.Fragment
import com.example.flex.services.FragmentService
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.Color.parseColor
import android.graphics.drawable.ColorDrawable



class MainActivity : AppCompatActivity(),
    LibraryFragment.OnFragmentInteractionListener,
    DownloadsFragment.OnFragmentInteractionListener,
    SettingsFragment.OnFragmentInteractionListener,
    FragNavController.TransactionListener
{
    //region Dependencies
    private val fragments = listOf(
        LibraryFragment(),
        DownloadsFragment(),
        SettingsFragment()
    )

    private val fragmentService = FragmentService(supportFragmentManager, fragments)
    //endregion

    //region FragNavController.TransactionListener Implementation
    override fun onFragmentTransaction(fragment: Fragment?, transactionType: FragNavController.TransactionType) {
        fragmentService.setDisplayHomeAsUpEnabled(supportActionBar)
    }

    override fun onTabTransaction(fragment: Fragment?, index: Int) {
        fragmentService.setDisplayHomeAsUpEnabled(supportActionBar)
    }
    //endregion

    //region OnFragmentInteractionListener Implementation
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    //endregion

    //region AppCompatActivity overrides
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set actionBar color
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(parseColor("#FFFFFFFF")))

        // FragmentService
        fragmentService.init(this, savedInstanceState)
        fragmentService.setOnNavigationItemSelectedListener(navigation)
    }

    override fun onBackPressed() {
        if(!fragmentService.onBackPressed())
            super.onBackPressed()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        fragmentService.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        createSearchOption(menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        fragmentService.onOptionsItemSelected(item)
        return true
    }
    //endregion


    // <-------------------- Public methods -------------------->
    // Library Fragment events
    fun openVideoPlayer(view: View){
        val intent = Intent(this, VideoActivity::class.java)

        intent.putExtra("index", view.movieId.text.toString().toInt())

        startActivity(intent)
    }

    fun onCardViewClicked(view: View){
        val newFragment = LibraryFragment(1)

        fragmentService.pushFragment(newFragment)
    }

    //region Private Methods
    private fun createSearchOption(menu : Menu?){
        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        if (menu != null) {
            (menu.findItem(R.id.search).actionView as SearchView).apply {
                setSearchableInfo(searchManager.getSearchableInfo(componentName))
                setIconifiedByDefault(false)
            }
        }
    }
    //endregion
}