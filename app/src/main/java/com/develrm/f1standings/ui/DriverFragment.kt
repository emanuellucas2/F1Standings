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
import com.develrm.f1standings.databinding.ActivityMainBinding
import com.develrm.f1standings.databinding.FragmentDriverBinding
import com.develrm.f1standings.ui.adapters.DriverAdapter
import com.develrm.f1standings.ui.base.BaseFragment
import com.develrm.f1standings.ui.state.ResourseState
import com.develrm.f1standings.viewmodel.DriverViewModel
import kotlinx.coroutines.launch

class DriverFragment : BaseFragment<FragmentDriverBinding, DriverViewModel>()  {

    override val viewModel: DriverViewModel by viewModels()

    private val driverAdapter by lazy { DriverAdapter() }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDriverBinding =
        FragmentDriverBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        collectObserver()
    }

    private fun collectObserver() = lifecycleScope.launch{
        viewModel.drivers.collect{ resource ->
            when(resource){
                is ResourseState.Success -> {
                    resource.data?.let{ values ->
                        binding.progressCircular.visibility = View.INVISIBLE
                        driverAdapter.drivers = values
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
        rvDriver.apply {
            adapter = driverAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }



}