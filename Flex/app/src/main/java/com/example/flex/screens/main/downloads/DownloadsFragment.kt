package com.example.flex.screens.main.downloads

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_library.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.flex.R
import com.example.flex.screens.main.library.MediaViewModel
import com.example.flex.screens.main.library.Movie
import com.example.flex.screens.main.library.RvMediaAdapter
import org.jetbrains.anko.longToast
import java.text.DecimalFormat


class DownloadsFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(com.example.flex.R.layout.fragment_downloads, container, false)

        root.recyclerView.addItemDecoration(
            DownloadsMarginItemDecoration(resources.getDimension(R.dimen.recyclerView_margin).toInt())
        )

        (activity as AppCompatActivity).supportActionBar!!.hide()

        updateDownloadsList(root)
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    @SuppressLint("WrongConstant")
    fun updateDownloadsList(root: View){
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
    }

    private fun fileSize(size:Int): String {
        val longSize = size.toLong()
        if (longSize <= 0) return "0"
        val units = arrayOf("B", "kB", "MB", "GB", "TB")
        val digitGroups = (Math.log10(longSize.toDouble()) / Math.log10(1024.0)).toInt()
        return DecimalFormat("#,##0.#").format(longSize / Math.pow(1024.0, digitGroups.toDouble())) + " " + units[digitGroups]
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
}
