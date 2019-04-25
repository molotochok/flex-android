package com.example.flex

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.View
import android.widget.SearchView
import com.example.flex.fragments.DownloadsFragment
import com.example.flex.fragments.LibraryFragment
import com.example.flex.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.adapter_item_layout.view.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), LibraryFragment.OnFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener, DownloadsFragment.OnFragmentInteractionListener {
    // Fields
    // Fragments
    private val libraryFragment  : Fragment = LibraryFragment()
    private val downloadsFragment: Fragment = DownloadsFragment()
    private val settingsFragment : Fragment = SettingsFragment()
    private var activeFragment   : Fragment = libraryFragment

    private val fm = supportFragmentManager

    // <-------------------- Public methods -------------------->
    // Override methods
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragmentsTransactions()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        if (menu != null) {
            (menu.findItem(R.id.search).actionView as SearchView).apply {
                setSearchableInfo(searchManager.getSearchableInfo(componentName))
                setIconifiedByDefault(false)
            }
        }

        return true
    }

    // Library Fragment events
    fun openVideoPlayer(view: View){
        val intent = Intent(this, VideoActivity::class.java)

        intent.putExtra("index", view.movieIndex.text.toString().toInt())

        startActivity(intent)
    }

    // <-------------------- Private methods -------------------->
    // Invokes when item is selected
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_library -> {
                changeFragment(libraryFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_downloads -> {
                changeFragment(downloadsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                changeFragment(settingsFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    private fun initFragmentsTransactions(){
        fm.beginTransaction().add(R.id.main_container, settingsFragment, "settings").hide(settingsFragment).commit()
        fm.beginTransaction().add(R.id.main_container, downloadsFragment, "downloads").hide(downloadsFragment).commit()
        fm.beginTransaction().add(R.id.main_container, libraryFragment, "library").commit()
    }
    private fun changeFragment(fragment: Fragment){
        if(activeFragment != fragment){
            fm.beginTransaction().hide(activeFragment).show(fragment).commit()
            activeFragment = fragment
        }
    }
}
