package com.sweatworks.homework.ui.splash

import com.sweatworks.homework.BR
import com.sweatworks.homework.R
import com.sweatworks.homework.databinding.FragmentSplashBinding
import com.sweatworks.homework.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding>(BR.viewModel, R.layout.fragment_splash) {
    override val viewModel: SplashViewModel by viewModel()
}