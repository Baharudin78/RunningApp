package com.baharudin.runningapp.ui.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.baharudin.runningapp.repository.MainRepository



class MainViewModel @ViewModelInject constructor(
        val repository: MainRepository
) : ViewModel(){

}