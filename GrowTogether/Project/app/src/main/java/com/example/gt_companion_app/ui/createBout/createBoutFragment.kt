package com.example.gt_companion_app.ui.createBout

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gt_companion_app.R
import com.example.gt_companion_app.viewmodels.CreateBoutViewModel

class createBoutFragment : Fragment() {

    companion object {
        fun newInstance() = createBoutFragment()
    }

    private lateinit var viewModel: CreateBoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_bout_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateBoutViewModel::class.java)
        // TODO: Use the ViewModel
    }

}