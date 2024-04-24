package com.develrm.f1standings.viewmodel

import androidx.lifecycle.ViewModel
import com.develrm.f1standings.data.model.DriverModel
import com.develrm.f1standings.data.repository.DriverRepository
import com.develrm.f1standings.ui.state.ResourseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DriverViewModel: ViewModel(){

    private val _drivers = MutableStateFlow<ResourseState<List<DriverModel>>>(
        ResourseState.Empty())
    val drivers: StateFlow<ResourseState<List<DriverModel>>> = _drivers

    init{
        fetch {
            _drivers.value = ResourseState.Success(it)
        }
    }

    fun fetch(callback: (List<DriverModel>) -> Unit) {
        DriverRepository.getInstance().getMyModels(callback)
    }

}