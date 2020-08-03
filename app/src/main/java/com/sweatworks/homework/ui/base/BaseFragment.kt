package com.sweatworks.homework.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.sweatworks.homework.common.extensions.subscribe
import com.sweatworks.homework.common.utils.Event
import com.sweatworks.homework.common.utils.IntentEvent

abstract class BaseFragment<T : ViewDataBinding>(
    private val viewModelId: Int,
    @LayoutRes private val layout: Int
) : Fragment() {

    abstract val viewModel: BaseViewModel
    lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            layout,
            container,
            false
        )

        binding.setVariable(viewModelId, viewModel)
        viewModel.snackBarError
            .subscribe(this, ::handleSnackBarError)
        viewModel.intentEvent
            .subscribe(this, ::handleIntentEvent)

        return binding.root
    }

    private fun handleSnackBarError(error: String) {
        Snackbar.make(requireView(), error, Snackbar.LENGTH_SHORT).show()
    }

    private fun handleIntentEvent(intentEvent: Event<IntentEvent>) {
        intentEvent.getContentIfNotHandled()?.let {
            val event = Intent(requireContext(), it.clazz.java)
            event.putExtra(BaseActivity.BASE_ARGUMENTS, it.arguments)
            if (it.finishCurrent) requireActivity().finish()
            startActivity(event)
        }
    }
}