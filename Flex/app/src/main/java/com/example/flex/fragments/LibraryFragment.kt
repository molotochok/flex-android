package com.example.flex.fragments

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
import android.widget.LinearLayout
import com.example.flex.R
import com.example.flex.adapters.RvMediaAdapter
import com.example.flex.decorators.MarginItemDecoration
import com.example.flex.models.Folder
import com.example.flex.models.Media
import com.example.flex.models.Movie
import kotlinx.android.synthetic.main.fragment_library.view.*


class LibraryFragment @SuppressLint("ValidFragment") constructor(private var index: Int = 0) : Fragment() {
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
        recyclerView.layoutManager =LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

        val data = getMediaList(index)

        val rvAdapter = RvMediaAdapter(data)
        recyclerView.adapter = rvAdapter
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
    private var mediaList = arrayListOf<ArrayList<Media>>(
        arrayListOf<Media>(
            Folder(1,
                "FOLDER example asd as das dasd asd ;lasd ;aspof oapsmf pasfm aslmf opas",
                posterPath = "https://i.imgur.com/6ATuUKH.jpg"),
            Movie(1,
                "The lord of the ringsCASCASCASASCASCSACASCSACASCSACASCASCASCASCA",
                duration = 190.2,
                resolution = "1080p",
                size="10.21 GiB",
                posterPath = "https://i.imgur.com/6ATuUKH.jpg"),
            Movie(2,
                "Wall-e",
                duration = 190.2,
                resolution = "1080p",
                size="5.15 GiB",
                posterPath = "https://i.imgur.com/VD8TMkx.jpg"),
            Movie(3,
                "Happy Boi",
                duration = 1.02,
                resolution = "1080p",
                size="150 Mb",
                posterPath = "https://i.imgur.com/H981AN7.jpg")
        ),
        arrayListOf<Media>(
            Movie(3,
                "Happy Boi",
                duration = 1.02,
                resolution = "1080p",
                size="150 Mb",
                posterPath = "https://i.imgur.com/H981AN7.jpg"),
            Folder(1,
                "FOLDER example asd as das dasd asd ;lasd ;aspof oapsmf pasfm aslmf opas",
                posterPath = "https://i.imgur.com/6ATuUKH.jpg")
            )
    )

    // TODO: Refactor this: move this to new class
    private fun getMediaList(index:Int) : ArrayList<Media>{
        return mediaList[index]
    }

    // Initializes recycleView with cardViews, which represents movie, audio, etc

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
