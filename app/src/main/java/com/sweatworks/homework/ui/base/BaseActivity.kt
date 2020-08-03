package com.sweatworks.homework.ui.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sweatworks.homework.R
import com.sweatworks.homework.databinding.BaseActivityBinding

abstract class BaseActivity(
    private val fragment: Fragment,
    private val backButton: Boolean = false,
    private val showActionBar: Boolean = true
) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<BaseActivityBinding>(
            this, R.layout.base_activity
        ).also {
            setSupportActionBar(it.toolbar)
            if (backButton) supportActionBar
                ?.setDisplayHomeAsUpEnabled(true)
            if (!showActionBar) supportActionBar?.hide()
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment.apply {
                    arguments = intent.extras
                }).commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val BASE_ARGUMENTS = "arg"
    }
}