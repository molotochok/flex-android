package com.example.flex.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.flex.R
import com.example.flex.adapters.RvAdapter
import com.example.flex.models.TestModel
import kotlinx.android.synthetic.main.fragment_library.*
import kotlinx.android.synthetic.main.fragment_library.view.*


class LibraryFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    //TODO: remove this
    private fun createTempList(root: View){
        var data = ArrayList<TestModel>()
        data.add(TestModel("KEKISH", 1))
        data.add(TestModel("JOHNY", 3))
        data.add(TestModel("Elisasfa", 5))
        data.add(TestModel("testins", 4))
        data.add(TestModel("The lord of the rings", 3))
        data.add(TestModel("The battle for middle eath", 2))
        data.add(TestModel("KEKISH", 1))
        data.add(TestModel("JOHNY", 3))
        data.add(TestModel("Elisasfa", 5))
        data.add(TestModel("testins", 4))
        data.add(TestModel("The lord of the rings", 3))
        data.add(TestModel("The battle for middle eath", 2))
        data.add(TestModel("KEKISH", 1))
        data.add(TestModel("JOHNY", 3))
        data.add(TestModel("Elisasfa", 5))
        data.add(TestModel("testins", 4))
        data.add(TestModel("The lord of the rings", 3))
        data.add(TestModel("The battle for middle eath", 2))

        val rvAdapter = RvAdapter(data)

        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        recyclerView.adapter = rvAdapter
    }

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

        //TODO: remove this
        createTempList(root)

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
