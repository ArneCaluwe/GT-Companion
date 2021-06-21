package com.example.gt_companion_app.ui.boutDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.growtogether.utils.Status
import com.example.gt_companion_app.adapters.BoutClickListener
import com.example.gt_companion_app.adapters.BoutDetailSmallAdapter
import com.example.gt_companion_app.data.local.bouts.BoutWithSets
import com.example.gt_companion_app.databinding.BoutListFragmentBinding
import com.example.gt_companion_app.viewmodels.BoutListViewModel
import org.koin.android.ext.android.inject

class BoutListFragment : Fragment() , BoutClickListener{

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: BoutListViewModel by inject()
        val binding = BoutListFragmentBinding.inflate(inflater, container, false)
        val adapter = BoutDetailSmallAdapter( this )
        binding.boutList.adapter = adapter;
        binding.boutList.setOnClickListener {

        }

        viewModel.bouts.observe(
            viewLifecycleOwner,
            Observer {
                it?.let { resource ->
                    when( resource.status ){
                        Status.SUCCESS -> {
                            adapter.submitList(resource.data)
                        }
                        Status.LOADING -> {
                            showProgress(true)
                        }
                        Status.ERROR -> {
                            showProgress(false)
                        }
                    }
                }
            }
        )

        return binding.root
    }

    private fun showProgress(b: Boolean) {

    }

    override fun onBoutClicked(bout: BoutWithSets) {
        navigateToDetail(bout)
    }

    private fun navigateToDetail(bout: BoutWithSets) {
        val action = BoutListFragmentDirections.actionNavBoutListToNavBoutDetail(bout.bout.boutId)
        findNavController().navigate(action)
    }
}