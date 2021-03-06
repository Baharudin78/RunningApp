package com.baharudin.runningapp.ui.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.baharudin.runningapp.R
import com.baharudin.runningapp.ui.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment(R.layout.fragment_settings) {
    private val viewModel : MainViewModel by viewModels()
}