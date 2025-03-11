package com.survivorinfotech.fragmentstuts

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class ProfileFragment : Fragment() {

    lateinit var myView: View
    lateinit var regDataSp: SharedPreferences

    lateinit var tvProfileText:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myView = inflater.inflate(R.layout.fragment_profile, container, false)

        tvProfileText = myView.findViewById(R.id.tvProfileText)
//        regDataSp = getSharedPreferences("regdatasp", Context.MODE_PRIVATE)
        regDataSp = requireContext().getSharedPreferences("regdatasp", Context.MODE_PRIVATE)

        tvProfileText.text = regDataSp.getString("useremail","")
        return myView
    }
}