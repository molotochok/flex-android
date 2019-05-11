package com.example.flex.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flex.R
import com.example.flex.adapters.RvDownloadMediaAdapter
import com.example.flex.decorators.DownloadsMarginItemDecoration
import com.example.flex.models.DownloadMedia
import com.example.flex.models.DownloadingMovie
import com.example.flex.models.DownloadStatus
import com.example.flex.models.DownloadedMovie
import kotlinx.android.synthetic.main.fragment_library.view.*

class DownloadsFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    private val list : ArrayList<DownloadMedia> = arrayListOf(
        DownloadingMovie(
            1,
            "Blade runner",
            10f,
            "MB/s",
            1.2f,
            "GB",
            6f,
            "GB",
            11,
            "min",
            "https://i.imgur.com/g5mdWLV.png",
            DownloadStatus.Downloading),
        DownloadingMovie(
            2,
            "Gladiator",
            20f,
            "MB/s",
            0.4f,
            "GB",
            2f,
            "GB",
            20,
            "min",
            "https://i.imgur.com/2LqPJXa.png",
            DownloadStatus.Downloading),
        DownloadingMovie(
            3,
            "Green mile",
            15f,
            "MB/s",
            0.1f,
            "GB",
            2.2f,
            "GB",
            24,
            "min",
            "https://i.imgur.com/HAFskrl.png",
            DownloadStatus.Downloading),
        DownloadedMovie(
            4,
            "Movie that is donwloaded",
            0.1f,
            "GB",
            2.2f,
            "GB",
            "https://i.imgur.com/2LqPJXa.png",
            DownloadStatus.Downloaded)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_downloads, container, false)

        root.recyclerView.addItemDecoration(
            DownloadsMarginItemDecoration(resources.getDimension(R.dimen.recyclerView_margin).toInt())
        )

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

    private fun getDownloadsList() : ArrayList<DownloadMedia>{
        return list
    }

    @SuppressLint("WrongConstant")
    fun updateDownloadsList(root: View){
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

        val data = getDownloadsList()

        val rvAdapter = RvDownloadMediaAdapter(data)
        recyclerView.adapter = rvAdapter
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
}
