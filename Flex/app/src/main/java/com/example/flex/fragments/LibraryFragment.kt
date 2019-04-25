package com.example.flex.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.flex.R
import com.example.flex.VideoActivity
import com.example.flex.adapters.RvAdapter
import com.example.flex.decorators.MarginItemDecoration
import com.example.flex.models.Movie
import kotlinx.android.synthetic.main.adapter_item_layout.view.*
import kotlinx.android.synthetic.main.fragment_library.*
import kotlinx.android.synthetic.main.fragment_library.view.*


class LibraryFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

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
             MarginItemDecoration(resources.getDimension(R.dimen.recyclerView_margin).toInt())
         )

        initMediaList(root)

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

    // Private methods
    // TODO: Refactor this: move this to new class
    private fun getMediaList() : ArrayList<Movie>{
        var mediaList = ArrayList<Movie>()
        mediaList.add(
            Movie(1,
                "The lord of the rings",
                duration = 190.2,
                resolution = "1080p",
                size="10.21 GiB",
                posterPath = "https://i.imgur.com/6ATuUKH.jpg"))
        mediaList.add(
            Movie(2,
                "Wall-e",
                duration = 190.2,
                resolution = "1080p",
                size="5.15 GiB",
                posterPath = "https://i.imgur.com/VD8TMkx.jpg"))
        mediaList.add(
            Movie(3,
                "Happy Boi",
                duration = 1.02,
                resolution = "1080p",
                size="150 Mb",
                posterPath = "https://i.imgur.com/H981AN7.jpg"))

        return mediaList
    }

    fun onkek(view: View){

    }

    // Initializes recycleView with cardViews, which represents movie, audio, etc
    private fun initMediaList(root: View){
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

        var data = getMediaList()

        val rvAdapter = RvAdapter(data)
        recyclerView.adapter = rvAdapter
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
