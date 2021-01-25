package com.baharudin.runningapp.ui.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.baharudin.runningapp.R
import com.baharudin.runningapp.ui.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupFragment : Fragment(R.layout.fragment_setup) {
    private val viewModel : MainViewModel by viewModels()
}