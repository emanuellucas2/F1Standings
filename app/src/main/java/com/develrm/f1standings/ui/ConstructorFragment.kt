package com.develrm.f1standings.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.develrm.f1standings.R
import com.develrm.f1standings.databinding.FragmentConstructorBinding
import com.develrm.f1standings.ui.adapters.ConstructorAdapter
import com.develrm.f1standings.ui.base.BaseFragment
import com.develrm.f1standings.ui.state.ResourseState
import com.develrm.f1standings.viewmodel.ConstructorViewModel
import kotlinx.coroutines.launch

class ConstructorFragment : BaseFragment<FragmentConstructorBinding, ConstructorViewModel>() {

    override val viewModel: ConstructorViewModel by viewModels()

    private val constructorAdapter by lazy { ConstructorAdapter() }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentConstructorBinding =
        FragmentConstructorBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        collectObserver()
    }

    private fun collectObserver() = lifecycleScope.launch{
        viewModel.constructors.collect{ resource ->
            when(resource){
                is ResourseState.Success -> {
                    resource.data?.let{ values ->
                        binding.progressCircular.visibility = View.INVISIBLE
                        constructorAdapter.constructor = values
                    }
                }
                is ResourseState.Error -> {
                    binding.progressCircular.visibility = View.INVISIBLE
                    resource.message?.let{ message ->
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.an_error_occurred),
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d("ListCharacterFragment","Error -> $message")
                    }
                }
                is ResourseState.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
                else -> {}
            }
        }
    }

    private fun setupRecyclerView() = with(binding){
        rvConstructor.apply {
            adapter = constructorAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}