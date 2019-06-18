package com.example.flex.screens.main.library

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit
import com.example.flex.R
import kotlinx.android.synthetic.main.fragment_library.view.*



class LibraryFragment @SuppressLint("ValidFragment") constructor(private var index: Int = 0) : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var viewModel: MediaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_library, container, false)

        root.recyclerView.addItemDecoration(
            LibraryMarginItemDecoration(resources.getDimension(R.dimen.recyclerView_margin).toInt())
         )

        (activity as AppCompatActivity).supportActionBar!!.show()

        updateMediaList(root)

        return root
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
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

    fun updateMediaList(root: View){
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(MediaViewModel::class.java)
        viewModel.getAllMedia().observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = RvMediaAdapter(it.map { media ->
                Movie(
                    media.id,
                    media.name,
                    duration(media.duration.toLong()),
                    "${media.width}x${media.height}",
                    fileSize(media.size),
                    media.thumbnail
                )
            })
        })
    }

    private fun fileSize(size:Int): String {
        val longSize = size.toLong()
        if (longSize <= 0) return "0"
        val units = arrayOf("B", "kB", "MB", "GB", "TB")
        val digitGroups = (Math.log10(longSize.toDouble()) / Math.log10(1024.0)).toInt()
        return DecimalFormat("#,##0.#").format(longSize / Math.pow(1024.0, digitGroups.toDouble())) + " " + units[digitGroups]
    }

    private fun duration(seconds: Long): String {
        return String.format(
            "%02d:%02d:%02d", TimeUnit.SECONDS.toHours(seconds),
            TimeUnit.SECONDS.toMinutes(seconds) % TimeUnit.HOURS.toMinutes(1),
            TimeUnit.SECONDS.toSeconds(seconds) % TimeUnit.MINUTES.toSeconds(1)
        )
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }


    // Companion objects
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LibraryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LibraryFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}
