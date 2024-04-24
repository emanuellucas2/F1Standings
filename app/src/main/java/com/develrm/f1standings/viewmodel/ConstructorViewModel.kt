package com.develrm.f1standings.viewmodel

import androidx.lifecycle.ViewModel
import com.develrm.f1standings.data.model.ConstructorModel
import com.develrm.f1standings.data.repository.ConstructorRepository
import com.develrm.f1standings.data.repository.DriverRepository
import com.develrm.f1standings.ui.state.ResourseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ConstructorViewModel: ViewModel(){

    private val _constructors = MutableStateFlow<ResourseState<List<ConstructorModel>>>(
        ResourseState.Empty())
    val constructors: StateFlow<ResourseState<List<ConstructorModel>>> = _constructors

    init{
        fetch {
            _constructors.value = ResourseState.Success(it)
        }
    }
    fun fetch(callback: (List<ConstructorModel>) -> Unit) {
        ConstructorRepository.getInstance().getMyModels(callback)
    }

}