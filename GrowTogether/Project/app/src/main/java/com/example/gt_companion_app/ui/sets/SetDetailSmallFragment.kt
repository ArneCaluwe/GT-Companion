package com.example.gt_companion_app.ui.sets

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gt_companion_app.R
import com.example.gt_companion_app.databinding.SetDetailSmallFragmentBinding
import com.example.gt_companion_app.viewmodels.BoutDetailViewModel
import com.example.gt_companion_app.viewmodels.SetDetailSmallViewModel
import org.koin.android.ext.android.bind
import org.koin.android.ext.android.inject

class SetDetailSmallFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: SetDetailSmallViewModel by inject()
        val binding = SetDetailSmallFragmentBinding.inflate(inflater, container, false)
        val set = binding.set

        binding.textView7.text = set?.set?.timeRemaining.toString()
        print("MY MESSAGE SHOULD BE BELOW!")
        print(set)
        return binding.root
    }


}